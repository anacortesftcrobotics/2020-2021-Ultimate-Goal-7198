# git_downloaded_file.py
import os, shutil
from pathlib import Path
from datetime import datetime, date, timedelta
from git import Repo

# Constants (value never changes)
PATH_DOWNLOADS = "Downloads"
PATH_BLOCKLY = "Blockly"
BLOCKLY_EXT = [".blk",".png"]
PATH_JAVA = "Java"
JAVA_EXT = [".zip"]
ALL_EXT = BLOCKLY_EXT + JAVA_EXT

def convert_date(timestamp):
    d = datetime.utcfromtimestamp(timestamp)
    formated_date = d.strftime('%Y%m%d')
    return formated_date

def fileInRepo(repo,path_to_file):
    rsub = repo.head.commit.tree
    for (_path, _stage), entry in repo.index.entries.items():
        #print(f"path: {_path}, stage: {_stage}")
        if _path == path_to_file.replace("\\","/"):
            return True
    return False

    # '''
    # repo is a gitPython Repo object
    # path_to_file is the full path to the file from the repository root
    # returns true if file is found in the repo at the specified path, false otherwise
    # '''
    # pathdir = os.path.dirname(path_to_file)
    # print(f"pathdir: {pathdir}")

    # # Build up reference to desired repo path
    # rsub = repo.head.commit.tree
    # for path_element in pathdir.split(os.path.sep):
    #     rsub = rsub[path_element]
    # print(f"rsub: {rsub}")
    # return(path_to_file in rsub)

def git_push(path_to_repo, files, commit_msg):
    try:
        repo = Repo(path_to_repo)
        # steps: add new or add update, commit all changes, push
        for file in files:
            repo.git.add(file, update=True)
            print(f"added/updated file: {file}")
        repo.index.commit(commit_msg)
        origin = repo.remote(name='origin')
        origin.push()
        repo.index.update()
    except:
        print('Some error occured while pushing the code')    

def move_files(entry, myPath):
    add_files = []
    if Path(entry).suffix.lower() in JAVA_EXT:
        # unpack works but need to get all the files unpacked!!
        srcPath = f"{Path(entry).parent}\\{Path(entry).stem}"
        destPath = f"{myPath}\\{PATH_JAVA}\\{Path(entry).stem}"
        
        shutil.unpack_archive(Path(entry).as_posix(), srcPath)
        for subdir, dirs, files in os.walk(srcPath):
            for filename in files:
                filepath = subdir + os.sep + filename
                print(f"Moved file: {filepath} to {destPath}{filepath.replace(srcPath,'')}")
                copied_file = shutil.move(f"{filepath}", f"{destPath}{filepath.replace(srcPath,'')}")
                add_files.append(copied_file)
        return add_files
    elif Path(entry).suffix.lower() in BLOCKLY_EXT:
        targetPath = f"{myPath}\\{PATH_BLOCKLY}"
        newPath = f"{targetPath}\\{Path(entry).name}"
        os.rename(entry, newPath)
        add_files.append(newPath)
        return add_files
    else:
        print("no files of coding format found")
        return add_files
    
if __name__ == '__main__':
    curPath = os.getcwd() 
    head1, tail1 = os.path.split(curPath)
    head2, tail2 = os.path.split(head1)
    fileDownload = f"{head2}\\{PATH_DOWNLOADS}"

    # if looking back in time
    # today = (date.today() + timedelta(days=-2)).strftime('%Y%m%d')
    today = date.today().strftime('%Y%m%d')
    repo = Repo(curPath)
    print(f"{repo}")
    entries = os.scandir(fileDownload)
    count = 0
    add_files = []

    for entry in entries:
        if Path(entry).suffix.lower() in ALL_EXT:
            info = entry.stat()
            filemtime = convert_date(info.st_mtime)
            # only look at today's files
            if (filemtime >= today):
                val = input(f"filename: {Path(entry).name}, modtime: {filemtime}, Do you wish to save to git?(Y/N): ")
                print(val) 
                # if user wants to move them. move them to current directory
                if val.lower() == 'yes' or val.lower() == 'y':
                    add_files = move_files(entry, curPath)  
            
    if len(add_files) > 0:
        cmt = input(f"Enter commit message for git: ")
        git_push(curPath, add_files, cmt)
    else:
        print(f"no files moved into git repo")