// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.Encoder;

public class DriveTrain extends SubsystemBase {
  Spark leftfront;
  Spark rightfront;
  Spark leftback;
  Spark rightback;
  Encoder drivingEncoder; 
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    leftfront=new Spark(Constants.leftfrontnumber);
    rightfront=new Spark(Constants.rightfrontnumber);
    leftback=new Spark(Constants.leftbacknumber);
    rightback=new Spark(Constants.rightbacknumber);
    drivingEncoder= new Encoder(2, 3);
  }
  public void tankdrive(double leftamount,double rightamount){
    leftback.set(leftamount);
    leftfront.set(leftamount);
    rightback.set(rightamount);
    rightfront.set(rightamount);
  }
  public void turnanddrive(double xAxis,double yAxis, double limiter){

    if (xAxis<-0.5){
      leftfront.set(xAxis);
      leftback.set(xAxis);
      rightfront.set(xAxis);
      rightback.set(xAxis);
    }
    else if (xAxis>0.5){
      leftback.set(xAxis);
      rightback.set(xAxis);
      leftfront.set(xAxis);
      rightfront.set(xAxis);
    }
    else{
      if (yAxis>limiter){
        yAxis=limiter;
      }
      else if (yAxis<-limiter){
        yAxis=-limiter;
      }
      else{
        leftback.set(-yAxis);
        rightback.set(yAxis);
        leftfront.set(-yAxis);
        rightfront.set(yAxis);
      }
    }
  }
  public void driveDistance(double distance){
    drivingEncoder.setDistancePerPulse(5);
    double position=drivingEncoder.getDistance();
    while(position-distance!=0){
      position=drivingEncoder.getDistance();
      if (position-distance<0){
        turnanddrive(-1, -1,1);
      }
      else if (position-distance>0){
        turnanddrive(1,1,1);
      }
    }
  }
  public double Turbo(double Boost){
    return Boost;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
