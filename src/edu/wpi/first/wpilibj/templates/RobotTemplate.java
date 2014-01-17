/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    /*
     * READ THIS BEFORE USING
     * 1 Trigger = stop/start compressor
     * 2 Top Back = activate piston 1
     * 3 Top Front = reverse piston 1
     * 4 Top Left = reverse piston 2
     * 5 Top Right = activate piston 2
     * 6 Base Left Forward = Disable all cylinders
     */
    
    int timeDelay = 1;
    
    int pressureSwitchChannel = 1;
    int relayChannel = 1;
    
    Compressor compressor = new Compressor(pressureSwitchChannel, relayChannel);
    DoubleSolenoid pistonOne = new DoubleSolenoid(1, 2);
    DoubleSolenoid pistonTwo = new DoubleSolenoid(3, 4);
    
    Joystick joystick = new Joystick(1);
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
//        compressor.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        if (joystick.getRawButton(1)) {
            if (compressor.enabled()) {
                compressor.stop();
            } else {
                compressor.start();
            }
        }
        if (joystick.getRawButton(6)) {
            pistonOne.set(DoubleSolenoid.Value.kOff);
            pistonTwo.set(DoubleSolenoid.Value.kOff);
        } else {
            if (joystick.getRawButton(2)) {
                pistonOne.set(DoubleSolenoid.Value.kForward);
                Timer.delay(timeDelay);
                pistonOne.set(DoubleSolenoid.Value.kOff);
            } else if (joystick.getRawButton(3)) {
                pistonOne.set(DoubleSolenoid.Value.kReverse);
                Timer.delay(timeDelay);
                pistonOne.set(DoubleSolenoid.Value.kOff);
            } else {
                
            }
            
            if (joystick.getRawButton(5)) {
                pistonTwo.set(DoubleSolenoid.Value.kForward);
                Timer.delay(timeDelay);
                pistonTwo.set(DoubleSolenoid.Value.kOff);
            } else if (joystick.getRawButton(4)) {
                pistonTwo.set(DoubleSolenoid.Value.kReverse);
                Timer.delay(timeDelay);
                pistonTwo.set(DoubleSolenoid.Value.kOff);
            } else {
                
            }
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
