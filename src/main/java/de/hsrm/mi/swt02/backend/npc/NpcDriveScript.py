import random

class NpcDriveScript():

    def __init__(self, x,z, streetRotation, carRotation, objectTypeId):
       
        self.carRotation = carRotation
        self.currentMapEle = MapEle(x,z,streetRotation,objectTypeId)
        self.nextUpperMapEle = MapEle(-1,-1,-1,-1)
        
        print('x_coord: {}'.format(self.currentMapEle.x_coord))
        print('z_coord: {}'.format(self.currentMapEle.z_coord))
        print('streetRotation: {}'.format(self.currentMapEle.streetRotation))
        print('carRotation: {}'.format(self.carRotation))
        print('streetId: {}'.format(self.currentMapEle.objectTypeId))
        
        
       
    def drive(self):
        print("script drive")
        if self.carRotation == 0:
            self.currentMapEle.x_coord -= 1
        elif self.carRotation == 1:
            self.currentMapEle.z_coord += 1
        elif self.carRotation == 2:
            self.currentMapEle.x_coord += 1
        elif self.carRotation == 3:
            self.currentMapEle.z_coord -= 1
        
        print('NEW car rotation: {}'.format(self.carRotation))
        print('NEW x_coord: {}'.format(self.currentMapEle.x_coord))
        print('NEW z_coord: {}'.format(self.currentMapEle.z_coord))
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
        if self.currentMapEle.streetRotation == 0:
            if self.carRotation == 0:
                self.carRotation += 1
            elif self.carRotation == 3:
                self.carRotation -= 1
                
        elif self.currentMapEle.streetRotation == 1:
            if self.carRotation == 1:
                self.carRotation += 1
            elif self.carRotation == 0:
                self.carRotation += 3
        
        elif self.currentMapEle.streetRotation == 2:
            if self.carRotation == 1:
                self.carRotation -= 1
            elif self.carRotation == 2:
                self.carRotation += 1

        elif self.currentMapEle.streetRotation == 3:
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

    def getCurrentXCoord(self):
        return self.currentMapEle.x_coord

    def getCurrentZCoord(self):
        return self.currentMapEle.z_coord

    def setNextUpperMapEle(self,MapEle):
        self.nextUpperMapEle = MapEle
        print("naechstes upperMapElement", MapEle)

    

class MapEle():
     def __init__(self,x,z,streetRotation,objectTypeId):
        self.x_coord = x
        self.z_coord = z
        self.streetRotation = streetRotation
        self.objectTypeId = objectTypeId
        


       