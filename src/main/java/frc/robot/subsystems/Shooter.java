// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.LineUpToShoot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;

import java.util.List;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder.Type;


public class Shooter extends SubsystemBase {
  private final I2C.Port colorsensorport=I2C.Port.kOnboard;
  private final ColorSensorV3 colorsensor = new ColorSensorV3(colorsensorport);
  private final ColorMatch colorMatcher = new ColorMatch();
  public static final Color blueBall = Color.kBlue;
  public static final Color redBall = Color.kRed;
  public static final Color otherstuff = Color.kYellow;
  public static final Color moreotherstuff = Color.kGray;
  private Color detectedColor;
  private String colorString;
  ColorMatchResult match;
  public static RelativeEncoder leftencoder;
  public static RelativeEncoder rightencoder;
  private static final int leftshooterdeviceID =3;
  private static final int rightshooterdeviceID =2;
  private static final int countsPerRev =4096;
  /** Creates a new Shooter. **/
  //Spark shooter1=new Spark(Constants.shooterrightnumber);
  //Spark shooter2=new Spark(Constants.shooterleftnumber);
  CANSparkMax leftshooter;
  CANSparkMax rightshooter;

  public Shooter() {
    colorMatcher.addColorMatch(blueBall);
    colorMatcher.addColorMatch(redBall);
    colorMatcher.setConfidenceThreshold(0.95);
    leftshooter=new CANSparkMax(leftshooterdeviceID, MotorType.kBrushed);
    rightshooter=new CANSparkMax(rightshooterdeviceID, MotorType.kBrushed);
    leftencoder=leftshooter.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, countsPerRev);
    rightencoder=rightshooter.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, countsPerRev);
    
    
  }
  public String getColor(){
    detectedColor=colorsensor.getColor();
    match=colorMatcher.matchClosestColor(detectedColor);
    if (match.color ==blueBall){
      colorString="Blue";
    }
    else if (match.color==redBall){
      colorString="Red";
    } 
    else{
      colorString="Grey Don't shoot";
    }
    return colorString;
  }
  public void shootball(double speed){
    leftshooter.set(-speed);
    rightshooter.set(speed);
    SmartDashboard.putNumber("left", leftencoder.getVelocity());
    SmartDashboard.putNumber("right", rightencoder.getVelocity());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
