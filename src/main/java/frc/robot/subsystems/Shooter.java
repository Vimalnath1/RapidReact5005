// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
/*import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;*/

public class Shooter extends SubsystemBase {
  /*private final I2C.Port colorsensorport=I2C.Port.kOnboard;
  private final ColorSensorV3 colorsensor = new ColorSensorV3(colorsensorport);
  private final ColorMatch colorMatcher = new ColorMatch();
  private Color detectedColor;
  private String colorString;
  ColorMatchResult match;
  /** Creates a new Shooter. */
  Spark shooter1=new Spark(2);
  Spark shooter2=new Spark(3);
  public Shooter() {
    //colorMatcher.setConfidenceThreshold(0.95);
  }
  /*public String getColor(){
    detectedColor=colorsensor.getColor();
    match=colorMatcher.matchClosestColor(detectedColor);
    if (match.color==)
  }*/
  public void shootball(double speed){
    shooter1.set(speed);
    shooter2.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
