import os

##quickly changes to the needed directory
os.chdir('/Users/User/InstantYummy/app/src/main/res/drawable')

## for every item in that navigated directory, print that item
for i in os.listdir():
    file_name,file_extension = os.path.splitext(i)
    firstcharacter = "a"
    finalfilename = firstcharacter + file_name
    for k in finalfilename:
        if k == "-":
            finalfilename = finalfilename.replace(k,'_')
            ##reintialize the variable
    os.rename(i,finalfilename + file_extension)
        
    