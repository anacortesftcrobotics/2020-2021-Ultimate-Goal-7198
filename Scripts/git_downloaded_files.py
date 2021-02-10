# git_downloaded_file.py
import os, shutil
from pathlib import Path
from datetime import datetime, date
from git import Repo

# Constants (value never changes)
PATH_DOWNLOADS = "Downloads"
PATH_BLOCKLY = "Blockly"
BLOCKLY_EXT = [".blk",".png"]
PATH_JAVA = "Java"
JAVA_EXT = [".zip"]

def convert_date(timestamp):
    d = datetime.utcfromtimestamp(timestamp)
    formated_date = d.strftime('%Y%m%d')
    return formated_date

def git_push(path_to_repo, files, commit_msg):
    try:
        repo = Repo(path_to_repo)
        for (_path, _stage), entry in index.entries.items():
          print(f"path: {_path}, stage: {_stage}")  

        # repo.git.add(update=True)
        # repo.index.commit(commit_msg)
        # origin = repo.remote(name='origin')
        # origin.push()
        # repo.index.update()
    except:
        print('Some error occured while pushing the code')    

def move_files(entry, myPath):
    add_files = []
    if Path(entry).suffix.lower() in JAVA_EXT:
        # unpack works but need to get all the files unpacked!!
        srcPath = f"{Path(entry).parent}\\{Path(entry).stem}"
        destPath = f"{myPath}\\{PATH_JAVA}\\{Path(entry).stem}\\"
        
        shutil.unpack_archive(Path(entry).as_posix(), srcPath)
        for file in os.listdir(srcPath):
            copied_file = shutil.copy2(file, destPath)
            print(copied_file)
            add_files.append(copied_file)
        print(f"Moved {srcPath} to {destPath}")
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

    today = date.today().strftime('%Y%m%d')
    repo = Repo(curPath)
    print(f"{repo}")
    entries = os.scandir(fileDownload)
    count = 0
    add_files = []

    for entry in entries:
        info = entry.stat()
        filemtime = convert_date(info.st_mtime)
        # only look at today's files
        if (filemtime >= today):
            val = input(f"filename: {Path(entry).name}, modtime: {filemtime}, Do you wish to save to git?(Y/N): ")
            print(val) 
            # if user wants to move them. move them to current directory
            if val.lower() == 'yes' or val.lower() == 'y':
                add_files = move_files(entry, curPath)  
            #     if Path(entry).suffix.lower() in BLOCKLY_EXT #== ".blk" or Path(entry).suffix.lower() == ".png": 
            #         newPath = f"{curPath}\\{PATH_BLOCKLY}\\{Path(entry).name}"
            #         os.rename(entry, newPath)
            #         print(f"Moved {Path(entry).name} to Blockly directory")
            #         add_files.append(newPath)
            #         #repo.index.add([newPath])
    if len(add_files) > 0:
        cmt = input(f"Enter commit message for git: ")
        for item in add_files:
            print(item)
        git_push(curPath,add_files, cmt)
    else:
        print(f"no files moved into git repo")