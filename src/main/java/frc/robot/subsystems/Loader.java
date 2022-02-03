// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Loader extends SubsystemBase {
  /** Creates a new Loader. */
  Spark loaderwindowmotor;
  Spark loadermotor1;
  Spark loadermotor2;
  public Loader() {
    loaderwindowmotor=new Spark(Constants.loaderwindowmotornumber);
    loadermotor1=new Spark(Constants.loadermotor1);
    loadermotor2=new Spark(Constants.loadermotor2);
  }
  public void loadball(double speed){
    loaderwindowmotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
