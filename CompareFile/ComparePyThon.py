
file = "Req5.txt"
expectedInputPath = "./SourceCode/expected_output/" + file
inputPath = "./SourceCode/output/" + file

f1 = open(expectedInputPath,'r')
f2 = open(inputPath,'r')

while (True):
    inputLine1 = f1.read().strip()
    inputLine2 = f2.read().strip()

    if(not inputLine1):
        print(True)
        break
    elif(inputLine1 == inputLine2):
        continue
    else: 
        print(inputLine1)
        print(inputLine2)        
        print(False)
        break
