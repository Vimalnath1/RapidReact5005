// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Climber extends SubsystemBase {
  CANSparkMax climber;
  /** Creates a new Climber. */
  public Climber() {
    climber=new CANSparkMax(4,MotorType.kBrushed);
    
  }
  public void robotclimb(double zAxis, boolean run){
    if (run){
    climber.set(zAxis);
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
