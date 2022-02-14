// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Loader extends SubsystemBase {
  /** Creates a new Loader. */
  Spark windowmotor;
  Spark loadermotor;
  public Loader() {
    loadermotor=new Spark(Constants.loadermotornumber);
    windowmotor=new Spark(Constants.windowmotornumber);
  }
  public void loadball(double speed){
    loadermotor.set(-speed);
  }
  public void releaseloader(double speed, boolean isInverted){
    if (isInverted==true){
    windowmotor.set(-speed);
    }
    else{
      windowmotor.set(speed);
    }
  }

  @Override
  public void periodic()   {
    // This method will be called once per scheduler run
  }
}
