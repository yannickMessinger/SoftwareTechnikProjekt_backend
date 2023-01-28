package de.hsrm.mi.swt02.backend.domain.map;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashMap;

//logic class to transfer frontend logic to backend

//@Entity
@Getter
@Setter
public class NpcAsset {
    
    //npcId
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;

    private int objectTypeId;
    
    //2D aus Editor
    private double x;
    private double y;

    //3D für pixelkoords
    //gameAssetX und gameAssetZ
    private double x3d;
    private double z3d;


    private int npcRotation;

    private String texture;
    
    @ManyToOne
    private MapObject mapObject;
    
    private int userId;






    private long npcId;
    //public positions: INpcPosition
    //private curMapObjCenterCoords: IMapObjCenterCoordinates
    private  int curMapObjCenterCoordsX;
    private  int curMapObjCenterCoordsZ;

    private MapObject curMapObj;
    private  MapObject nextMapObj;
    private int fieldSize;
    private int mapLimit;

    //müsste eig x3d und z3d sein
    //public gameAssetX: number
    //public gameAssetZ: number
    
    private boolean driving;
    private boolean needsMapEleUpdate;
    private int lastCarRotation;
    private double viewRotation;
    private HashMap<Integer,Double> rotationMap; //<int, int>
    private double velocity;
    //private long objectTypeId;

    private double curveRadius;
    private int curveCenterX;
    private int curveCenterZ;
    private boolean driveCurveRight;
    private double currCurveAngle;
    private double curveAngleInc;

    public NpcAsset() {
    }
    //npcId: number,objectTypeId: number,gameAssetX: number,gameAssetZ: number,npcRotation: number,fieldSize: number,curMapObj: IMapObject
    public NpcAsset(int objectTypeId, double x, double y, int rotation, String texture, int userId, MapObject curMapObj) {
        this.objectTypeId = objectTypeId;
        this.x = x;
        this.y = y;
        this.npcRotation = rotation;
        this.texture = texture;
        this.userId = userId;
        this.x3d = 0.0;
        this.z3d = 0.0;
        this.fieldSize = 10;
        this.curMapObj = curMapObj;
        this.nextMapObj = new MapObject();
        this.rotationMap = new HashMap<Integer,Double>();
        this.fillRotationMap();
        this.velocity = 0.05;
        this.mapLimit = 0;
        this.driving = true;
        this.needsMapEleUpdate = true;
        this.lastCarRotation = this.npcRotation;
        this.viewRotation = this.rotationMap.get(this.npcRotation);

        this.curveRadius = 0;
        this.curveCenterX = 0;
        this.curveCenterZ = 0;
        this.driveCurveRight = false;
        this.currCurveAngle = 0.5;
        this.curveAngleInc = 0.5;

        this.calcNpcMapLimit();
    }


    private void fillRotationMap(){
        this.rotationMap.put(0, Math.PI);
        this.rotationMap.put(1, (Math.PI / 2));
        this.rotationMap.put(2, 0.0);
        this.rotationMap.put(3, ((3 * Math.PI) / 2));
    }

    

    public void move() {
        if (
            this.curMapObj.getObjectTypeId() == 0 ||
            this.curMapObj.getObjectTypeId() == 12 ||
            this.curMapObj.getObjectTypeId() == 9 ||
            this.curMapObj.getObjectTypeId() == 11
        ) {
            this.moveStraight();
        } else if (this.curMapObj.getObjectTypeId() == 1 || this.curMapObj.getObjectTypeId()== 10) {
            this.moveCurve();
        } else if (this.curMapObj.getObjectTypeId() == 2) {
            if (this.lastCarRotation == this.npcRotation) {
                this.moveStraight();
            } else {
                this.moveCurve();
            }
        }
    }



    public void moveStraight(){
        if (this.npcRotation == 0) {
            this.z3d -= this.velocity;
        } else if (this.npcRotation == 1) {
            this.x3d += this.velocity;
        } else if (this.npcRotation == 2) {
            this.z3d += this.velocity;
        } else if (this.npcRotation == 3) {
            this.x3d -= this.velocity;
        }
    }


    public void moveCurve(){
        if (
            !(
                this.currCurveAngle == 0 ||
                this.currCurveAngle == 90 ||
                this.currCurveAngle == 180 ||
                this.currCurveAngle == 270 ||
                this.currCurveAngle == 360
            )
        ) {
            this.calculateCurvePoints();
        } else {
            this.moveStraight();
        }
    }


    public void calculateCurvePoints() {
        this.x3d = this.curveCenterX + Math.cos((this.currCurveAngle * Math.PI) / 180) * this.curveRadius;
        this.z3d = this.curveCenterZ - Math.sin((this.currCurveAngle * Math.PI) / 180) * this.curveRadius;
        this.currCurveAngle += this.curveAngleInc;

        if (this.driveCurveRight) {
            this.viewRotation -= 0.5 * (Math.PI / 180);
        } else {
            this.viewRotation += 0.5 * (Math.PI / 180);
        }
    }


    public void calcNpcMapLimit(){
        if (this.npcRotation == 0) {
            this.mapLimit = this.curMapObj.getCenterZ3d() - this.fieldSize / 2;
        } else if (this.npcRotation == 1) {
            this.mapLimit = this.curMapObj.getCenterX3d() + this.fieldSize / 2;
        } else if (this.npcRotation == 2) {
            this.mapLimit = this.curMapObj.getCenterZ3d() + this.fieldSize / 2;
        } else if (this.npcRotation == 3) {
            this.mapLimit = this.curMapObj.getCenterX3d() - this.fieldSize / 2;
        }
    }

    
    public boolean reachedMapEleLimit(){
        if (this.npcRotation == 0) {
            if (this.z3d > this.mapLimit) {
                return false;
            } else {
                //this.driving = false
                this.needsMapEleUpdate = true;
                //console.log("fahre nicht 0")
                return true;
            }
        } else if (this.npcRotation == 1) {
            if (this.x3d < this.mapLimit) {
                return false;
            } else {
                //this.driving = false
                this.needsMapEleUpdate = true;
                //console.log("fahre nicht 1")
                return true;
            }
        } else if (this.npcRotation == 2) {
            if (this.z3d < this.mapLimit) {
                return false;
            } else {
                //this.driving = false
                this.needsMapEleUpdate = true;
                //console.log("fahre nicht 2")
                return true;
            }
        } else if (this.npcRotation == 3) {
            if (this.x3d > this.mapLimit) {
                return false;
            } else {
                //this.driving = false
                this.needsMapEleUpdate = true;
                //console.log("fahre nicht 3")
                return true;
            }
        } else {
            //logger FEHLER
            return false;
        }
    }

    public void calculateIntersection(){
        if (
            (this.lastCarRotation == 0 && this.npcRotation == 1) ||
            (this.lastCarRotation == 3 && this.npcRotation == 2)
        ) {
            this.curveCenterX = this.curMapObj.getCenterX3d() + this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() + this.fieldSize / 2;

            if (this.lastCarRotation == 0) {
                this.driveCurveRight = true;
                this.curveRadius = this.curveCenterX - this.x3d;
                this.currCurveAngle = 179.5;
                this.curveAngleInc = -0.5;
            } else {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 90.5;
                this.curveAngleInc = 0.5;
            }
        } else if (
            (this.lastCarRotation == 0 && this.npcRotation == 3) ||
            (this.lastCarRotation == 1 && this.npcRotation == 2)
        ) {
            this.curveCenterX = this.curMapObj.getCenterX3d() - this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() + this.fieldSize / 2;

            if (this.lastCarRotation == 0) {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterX - this.x3d);
                this.currCurveAngle = 0.5;
                this.curveAngleInc = 0.5;
            } else {
                this.driveCurveRight = true;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 89.5;
                this.curveAngleInc = -0.5;
            }
        } else if (
            (this.lastCarRotation == 1 && this.npcRotation == 0) ||
            (this.lastCarRotation == 2 && this.npcRotation == 3)
        ) {
            this.curveCenterX = this.curMapObj.getCenterX3d() - this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() - this.fieldSize / 2;

            if (this.lastCarRotation == 2) {
                this.driveCurveRight = true;
                this.curveRadius = Math.abs(this.curveCenterX - this.x3d);
                this.currCurveAngle = 359.5;
                this.curveAngleInc = -0.5;
            } else {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 270.5;
                this.curveAngleInc = 0.5;
            }
        } else if (
            (this.lastCarRotation == 2 && this.npcRotation == 1) ||
            (this.lastCarRotation == 3 && this.npcRotation == 0)
        ) {
            this.curveCenterX = this.curMapObj.getCenterX3d() + this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() - this.fieldSize / 2;

            if (this.lastCarRotation == 3) {
                this.driveCurveRight = true;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 269.5;
                this.curveAngleInc = -0.5;
            } else {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterX - this.x3d);
                this.currCurveAngle = 180.5;
                this.curveAngleInc = 0.5;
            }
        } else if (this.lastCarRotation == this.npcRotation) {
            this.moveStraight();
        }
    }

    public void calculateCurve(){
        if (this.curMapObj.getRotation() == 0) {
            this.curveCenterX = this.curMapObj.getCenterX3d() + this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() + this.fieldSize / 2;

            if (this.lastCarRotation == 0) {
                this.driveCurveRight = true;
                this.curveRadius = this.curveCenterX - this.x3d;
                this.currCurveAngle = 179.5;
                this.curveAngleInc = -0.5;
            } else if (this.lastCarRotation == 3) {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 90.5;
                this.curveAngleInc = 0.5;
            } else {
                //logger fehler
            }
        } else if (this.curMapObj.getRotation() == 1) {
            this.curveCenterX = this.curMapObj.getCenterX3d() - this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() + this.fieldSize / 2;
            if (this.lastCarRotation == 1) {
                this.driveCurveRight = true;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 89.5;
                this.curveAngleInc = -0.5;
            } else if (this.lastCarRotation == 0) {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterX - this.x3d);
                this.currCurveAngle = 0.5;
                this.curveAngleInc = 0.5;
            } else {
                //logger fehler
            }
        } else if (this.curMapObj.getRotation() == 2) {
            this.curveCenterX = this.curMapObj.getCenterX3d() - this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() - this.fieldSize / 2;
            if (this.lastCarRotation == 2) {
                this.driveCurveRight = true;
                this.curveRadius = Math.abs(this.curveCenterX - this.x3d);
                this.currCurveAngle = 359.5;
                this.curveAngleInc = -0.5;
            } else if (this.lastCarRotation == 1) {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 270.5;
                this.curveAngleInc = 0.5;
            } else {
                //logger fehler
            }
        } else if (this.curMapObj.getRotation() == 3) {
            this.curveCenterX = this.curMapObj.getCenterX3d() + this.fieldSize / 2;
            this.curveCenterZ = this.curMapObj.getCenterZ3d() - this.fieldSize / 2;
            if (this.lastCarRotation == 3) {
                this.driveCurveRight = true;
                this.curveRadius = Math.abs(this.curveCenterZ - this.z3d);
                this.currCurveAngle = 269.5;
                this.curveAngleInc = -0.5;
            } else if (this.lastCarRotation == 2) {
                this.driveCurveRight = false;
                this.curveRadius = Math.abs(this.curveCenterX - this.x3d);
                this.currCurveAngle = 180.5;
                this.curveAngleInc = 0.5;
            } else {
               //logger fehler
            }
        }
    }

    public void setClientNpcPosition(double npcPosX, double npcPosZ, int npcRotation, double viewRotation) {
        this.x3d = npcPosX;
        this.z3d = npcPosZ;
        this.npcRotation = npcRotation;
        this.viewRotation = viewRotation;
    }
    
}
