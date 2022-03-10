// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Climber extends SubsystemBase {
  CANSparkMax climber;
  DigitalInput toplimitswitch;
  DigitalInput bottomlitmitswitch;
  /** Creates a new Climber. */
  public Climber() {
    climber=new CANSparkMax(3,MotorType.kBrushed);
    toplimitswitch=new DigitalInput(0);
    bottomlitmitswitch=new DigitalInput(1);
  }
  public void robotclimb(double zAxis, boolean run){
    if (run){
    climber.set(zAxis);
    }
    else if (run==false){
      climber.set(0);
    }
    if (toplimitswitch.get() || bottomlitmitswitch.get()){
      climber.set(0);
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
