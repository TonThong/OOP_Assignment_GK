def isTrueOfFile(file,expectedInputPath,inputPath):
    try :
        expectedInputPath +=file
        inputPath +=file

        f1 = open(expectedInputPath,'r')
        f2 = open(inputPath,'r')

        while (True):
            inputLine1 = f1.read().strip()
            inputLine2 = f2.read().strip()

            if(not inputLine1):
                print(file,end=" ")       
                print(True)
                break
            elif(inputLine1 == inputLine2):
                continue
            else: 
                print(file,end=" ")       
                print(False)
                break
    except:
        print(file,end=" ")  
        print("Error")

fileList = ["Req1.txt","Req2_1.txt","Req2_2.txt","Req3_1.txt","Req3_2.txt","Req4_1.txt","Req4_2.txt","Req5.txt"]

# for file in fileList:
#     isTrueOfFile(file,"./SourceCode/expected_output/","./SourceCode/output/")

