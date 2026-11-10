// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The methods in this class are called automatically corresponding to each
 * mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the
 * package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  public Robot() {
  }

  private SparkMax leftMotor1 = new SparkMax(1, MotorType.kBrushed);
  private SparkMax leftMotor2 = new SparkMax(11, MotorType.kBrushed);
  private SparkMax rightMotor1 = new SparkMax(2, MotorType.kBrushed);
  private SparkMax rightMotor2 = new SparkMax(21, MotorType.kBrushed);

  private Joystick joystick = new Joystick(0);

  private double startTime;

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < Constants.timeToTurn) {

      drive(Constants.driveSpeed);

    } else if (time - startTime < Constants.endOfTurn) {

      drive(-Constants.turnSpeed, Constants.turnSpeed + 0.05);

    } else if (time - startTime < Constants.timeToStop) {

      drive(Constants.driveSpeed);
    } else {
      drive(0, 0);
    }

  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    double speed = -joystick.getRawAxis(1) * 0.5;
    double turn = -joystick.getRawAxis(4) * 0.3;

    System.out.println("Speed: " + speed + "  Turn: " + turn);

    double left = speed + turn;
    double right = -speed + turn;

    leftMotor1.set(left);
    leftMotor2.set(left);
    rightMotor1.set(right);
    rightMotor2.set(right);

    System.out.println("Speed LM1: " + leftMotor1.get());
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }

  // User Defined Methods
  /**
   * Drive method that sets the same speed to all motors
   * @param speed
   */
  public void drive(double speed) {
    leftMotor1.set(speed);
    leftMotor2.set(speed);
    rightMotor1.set(-speed);
    rightMotor2.set(-speed);
  }

  /**
   * Robot-centric control of Noodle's tank drive motors
   * 
   * @param speedLeft  speed set to the motors on the left side from -1.0 to 1.0
   * @param speedRight speed set to the motors on the right side from -1.0 to 1.0.
   *                   This value will be inverted by the method.
   */
  public void drive(double speedLeft, double speedRight) {
    leftMotor1.set(speedLeft);
    leftMotor2.set(speedLeft);
    rightMotor1.set(-speedRight);
    rightMotor2.set(-speedRight);
  }
}
