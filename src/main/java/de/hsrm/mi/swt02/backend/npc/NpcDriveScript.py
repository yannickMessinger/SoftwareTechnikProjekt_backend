import random

class NpcDriveScript():

    def __init__(self, x,z, streetRotation, carRotation, streetId):
        self.x_coord = x
        self.z_coord = z
        self.streetRotation = streetRotation
        self.carRotation = carRotation
        self.streetId = streetId
        print(self.x_coord)
        print(self.z_coord)
        print(self.streetRotation)
        print(self.carRotation)
        print(self.streetId)
        
       
    def drive(self):
        print("script drive")
        if self.carRotation == 0:
            self.z_coord += 1
        elif self.carRotation == 1:
            self.x_coord += 1
        elif self.carRotation == 2:
            self.z_coord -= 1
        elif self.carRotation == 3:
            self.x_coord -= 1
        

    def intersectionStreet(self):
        num = random.randint(-1,1)
        self.carRotation += num
        if(self.carRotation > 3):
            self.carRotation = 0
        elif(self.carRotation < 0):
            self.carRotation = 3

        self.drive()

    def curveStreet(self):
        if self.streetRotation == 0:
            if self.carRotation == 0:
                self.carRotation += 1
            elif self.carRotation == 3:
                self.carRotation -= 1
                
        elif self.streetRotation == 1:
            if self.carRotation == 1:
                self.carRotation += 1
            elif self.carRotation == 0:
                self.carRotation += 3
        
        elif self.streetRotation == 2:
            if self.carRotation == 1:
                self.carRotation -= 1
            elif self.carRotation == 2:
                self.carRotation += 1

        elif self.streetRotation == 3:
            if self.carRotation == 2:
                self.carRotation -= 1
            elif self.carRotation == 3:
                self.carRotation = 0
        
        self.drive()       
    
    
    def checkRotation(self):
        if(self.carRotation > 3):
            self.carRotation = 0
        elif(self.carRotation < 0):
            self.carRotation = 3

    def retX(self):
        print("retX")
        return self.x_coord
    

print("Marc stinkt nicht viel")



       