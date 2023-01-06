import random

class NpcDriveScript():

    def __init__(self, x,z, streetRotation, carRotation, streetId):
        self.x_coord = x
        self.z_coord = z
        self.streetRotation = streetRotation
        self.carRotation = carRotation
        self.streetId = streetId
        print('x_coord: {}'.format(self.x_coord))
        print('z_coord: {}'.format(self.z_coord))
        print('streetRotation: {}'.format(self.streetRotation))
        print('carRotation: {}'.format(self.carRotation))
        print('streetId: {}'.format(self.streetId))
        
        
       
    def drive(self):
        print("script drive")
        if self.carRotation == 0:
            self.x_coord -= 1
        elif self.carRotation == 1:
            self.z_coord += 1
        elif self.carRotation == 2:
            self.x_coord += 1
        elif self.carRotation == 3:
            self.z_coord -= 1
        
        print('NEW car rotation: {}'.format(self.carRotation))
        print('NEW x_coord: {}'.format(self.x_coord))
        print('NEW z_coord: {}'.format(self.z_coord))
        print("\n")

    def intersectionStreet(self):
        num = random.randint(-1,1)
        self.carRotation += num
        if(self.carRotation > 3):
            self.carRotation = 0
        elif(self.carRotation < 0):
            self.carRotation = 3

        self.drive()

    def curveStreet(self):
        print("curve")
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

    def getCarRot(self):
       
        return self.carRotation

    def getXCoord(self):
        return self.x_coord

    def getZCoord(self):
        return self.z_coord

    



       