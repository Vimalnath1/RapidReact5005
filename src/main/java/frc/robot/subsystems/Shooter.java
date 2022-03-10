// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
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
  private SparkMaxPIDController pidController;
  private RelativeEncoder leftencoder;
  private RelativeEncoder rightencoder;
  private static final int leftshooterdeviceID =4;
  private static final int rightshooterdeviceID =2;
  private static final int countsPerRev = 4096;

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
    leftencoder= leftshooter.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, countsPerRev);
    rightencoder=rightshooter.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, countsPerRev);
    leftshooter.restoreFactoryDefaults();
    rightshooter.restoreFactoryDefaults();
    pidController=leftshooter.getPIDController();
    pidController.setFeedbackDevice(leftencoder);
    double kP = 0.1; 
    double kI = 1e-4;
    double kD = 1; 
    double kIz = 0; 
    double kFF = 0; 
    double kMaxOutput = 1; 
    double kMinOutput = -1;
    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setIZone(kIz);
    pidController.setFF(kFF);
    pidController.setOutputRange(kMinOutput, kMaxOutput);
    SmartDashboard.putNumber("Velocity", leftencoder.getVelocity());
  }
  public String getColor(){
    detectedColor=colorsensor.getColor();
    match=colorMatcher.matchClosestColor(detectedColor);
    if (match.color==blueBall){
      colorString="Blue Ball";
    }
    else if (match.color==redBall){
      colorString="Red Ball";
    } 
    else{
      colorString="Grey Don't shoot";
    }
    return colorString;
  }
  public void shootball(double speed){
    leftshooter.set(speed);
    rightshooter.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
