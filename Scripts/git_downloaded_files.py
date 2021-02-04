# git_downloaded_file.py
import os
from pathlib import Path
from datetime import datetime, date
from git import Repo


def convert_date(timestamp):
    d = datetime.utcfromtimestamp(timestamp)
    formated_date = d.strftime('%Y%m%d')
    return formated_date

if __name__ == '__main__':
    curPath = os.getcwd() 
    head1, tail1 = os.path.split(curPath)
    head2, tail2 = os.path.split(head1)
    fileDownload = f"{head2}\\Downloads"

    today = date.today().strftime('%Y%m%d')
    repo = Repo(curPath)
    print(f"{repo}")
    entries = os.scandir(fileDownload)
    count = 0
    for entry in entries:
        info = entry.stat()
        filemtime = convert_date(info.st_mtime)
        # only look at today's files
        if (filemtime >= today):
            val = input(f"filename: {Path(entry).name}, modtime: {filemtime}, Do you wish to save to git?(Y/N): ")
            print(val) 
            # if user wants to move them. move them to current directory
            if val.lower() == 'yes' or val.lower() == 'y':
                print(f"path ext: {Path(entry).suffix.lower()}")
                if Path(entry).suffix.lower() == ".blk" or Path(entry).suffix.lower() == ".png": 
                    newPath = f"{curPath}\\Blockly\\{Path(entry).name}"
                    os.rename(entry, newPath)
                    count = count + 1
                    print("Moved to Blockly directory")
    #                 repo.index.add([newPath])
    if count > 0:
        cmt = input(f"Enter commit message for git: ")
        #repo.index.commit(cmt)
    else:
        print(f"no files moved into git repo")