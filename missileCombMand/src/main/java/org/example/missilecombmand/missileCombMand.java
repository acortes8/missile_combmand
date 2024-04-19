//Alejandro Cortes

package org.example.missilecombmand;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;

public class missileCombMand extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    private AnimationTimer timer;
    private final int width = 640, height = 640, horizon = 575;
    private Turret turret;
    private Beehive beehiveOne;
    private Beehive beehiveTwo;
    private Beehive beehiveThree;
    private Beehive beehiveFour;
    private ArrayList<Beehive> beehiveTargets;
    private HoneyMissile honeyMissile;
    private WaspMissile waspMissileOne;
    private WaspMissile waspMissileTwo;
    private WaspMissile waspMissileThree;
    private WaspMissile waspMissileFour;
    private Title title;
    private Score score;
    private Canvas canvas;
    private Stage stage;

    public void start(Stage stage) {
        turret = new Turret();
        beehiveOne = new Beehive(width / 6.0);
        beehiveTwo = new Beehive(2 * width / 6.0);
        beehiveThree = new Beehive(4 * width / 6.0);
        beehiveFour = new Beehive(5 * width / 6.0);
        beehiveTargets = new ArrayList<>(Arrays.asList(beehiveOne, beehiveTwo, beehiveThree, beehiveFour));
        waspMissileOne = new WaspMissile();
        waspMissileTwo = new WaspMissile();
        waspMissileThree = new WaspMissile();
        waspMissileFour = new WaspMissile();
        honeyMissile = new HoneyMissile();
        score = new Score();
        title = new Title();

        canvas = new Canvas(width, height);
        this.stage = stage;

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Missile Comb-mand -- Aim: arrow keys / Shoot: space then space again");
        stage.setResizable(false);

        scene.setOnKeyPressed( evt -> {
            KeyCode code = evt.getCode();
            if(code == KeyCode.LEFT && turret.angle > 1.37881010907552 && !honeyMissile.isFired) {
                if(!title.hasStart) {
                    title.hasStart = true;
                }
                turret.angle -= (Math.PI / 180);
                turret.endPointX = turret.centerX - 25 * Math.cos(turret.angle * 5);
                turret.endPointY = turret.centerY - 25 * Math.sin(turret.angle * 5);
            }
            else if(code == KeyCode.RIGHT && turret.angle < 1.762782544514273 && !honeyMissile.isFired) {
                if(!title.hasStart) {
                    title.hasStart = true;
                }
                turret.angle += (Math.PI / 180);
                turret.endPointX = turret.centerX - 25 * Math.cos(turret.angle * 5);
                turret.endPointY = turret.centerY - 25 * Math.sin(turret.angle * 5);
            }
            else if(code == KeyCode.SPACE) {
                if(!title.hasStart) {
                    title.hasStart = true;
                }
                if (!honeyMissile.isFired) {
                    honeyMissile.isFired = true;
                } else {
                    honeyMissile.isIgnited = true;
                }
            }
        });

        stage.focusedProperty().addListener((obj, oldVal, newVal) -> {

            if(newVal) {
                timer.start();
            } else {
                timer.stop();
            }
            draw();
        });

        timer = new AnimationTimer() {
            long previousFrameTime;

            public void handle(long time) {
                if(time - previousFrameTime > 0.99e9/60) {
                    turret.updateForNewFrame();
                    beehiveOne.updateForNewFrame();
                    beehiveTwo.updateForNewFrame();
                    beehiveThree.updateForNewFrame();
                    beehiveFour.updateForNewFrame();
                    waspMissileOne.updateForNewFrame();
                    waspMissileTwo.updateForNewFrame();
                    waspMissileThree.updateForNewFrame();
                    waspMissileFour.updateForNewFrame();
                    honeyMissile.updateForNewFrame();
                    title.UpdateForNewFrame();
                    draw();
                    previousFrameTime = time;
                }
            }
        };

        stage.show();
        timer.start();
    }

    public void draw() {
        //Presentation Note: Replace sky blue below with black for high contrast
        //Presentation Note: Replace forest green below with light gray for high contrast

        GraphicsContext g = canvas.getGraphicsContext2D();

        g.setFill(Color.SKYBLUE);
        g.fillRect(0, 0, width, height);

        g.setFill(Color.FORESTGREEN);
        g.fillRect(0, horizon, width, height);

        if(stage.isFocused()) {
            g.setStroke(Color.SKYBLUE);
        } else {
            g.setFont(Font.font("Trebuchet", FontWeight.EXTRA_BOLD, 18));
            g.setFill(Color.BLACK);
            g.fillText("(Game Paused)", 20, height - 30);
            g.setStroke(Color.DARKGRAY);
        }

        g.setLineWidth(3);
        g.strokeRect(1.5, 1.5, width - 3, height - 3);

        turret.draw(g);
        beehiveOne.draw(g);
        beehiveTwo.draw(g);
        beehiveThree.draw(g);
        beehiveFour.draw(g);
        waspMissileOne.draw(g);
        waspMissileTwo.draw(g);
        waspMissileThree.draw(g);
        waspMissileFour.draw(g);
        honeyMissile.draw(g);
        score.draw(g);
        title.draw(g);
    }

    private class Turret {
        double endPointX, endPointY;
        double centerX, centerY;
        double angle = Math.PI / 2;

        Turret() {
            centerX = width / 2.0;
            centerY = horizon;
            endPointX = centerX;
            endPointY = centerY - 25;
        }

        void updateForNewFrame() {

        }

        void draw(GraphicsContext g) {
            //Presentation Note: Replace colors below with green for high contrast
            g.setFill(Color.BLACK);
            g.fillOval(centerX - 12.5, centerY - 12.5, 25, 25);
            g.setLineWidth(8);
            g.setStroke(Color.BLACK);
            g.strokeLine(centerX, centerY, endPointX, endPointY);
        }
    }

    private class Beehive {
        double targetX;
        Image beehivePNG;
        boolean isExploding, isAlive;
        int explodedCounter;

        Beehive(double x) {
            //Presentation Note: Replace url below with "beehiveHighContrast.png" for high contrast version
            beehivePNG = new Image("beehive.png"); //32 pixels by 48 pixels
            targetX = x;
            isExploding = false;
            isAlive = true;
        }

        void updateForNewFrame() {
            if(isExploding) {
                explodedCounter++;

                if(explodedCounter == 50) {
                    isExploding = false;
                    explodedCounter = 0;
                    beehiveTargets.remove(this);
                    isAlive = false;
                }
            }
        }

        void draw(GraphicsContext g) {
            //Presentation Note: Replace color below with red for high contrast
            if(isAlive) {
                g.drawImage(beehivePNG, targetX - 16, horizon - 40);
            }

            if(isExploding) {
                g.setFill(Color.FIREBRICK);
                g.fillOval(targetX - 0.75 * explodedCounter, horizon - 0.75 * explodedCounter, 1.5 * explodedCounter, 1.5 * explodedCounter);
            }
        }

    }

    private class WaspMissile {
        Beehive target;
        double startX, startY, currX, currY, targetX, targetY;
        double trajectoryX, trajectoryY, trajectorySlope;
        int missileSpeed;

        WaspMissile() {
            startX = Math.random() * width;
            startY = 0;
            currX = startX;
            currY = startY;
            target = beehiveTargets.get((int) (Math.random() * beehiveTargets.size()));
            targetX = target.targetX;
            targetY = horizon;
            missileSpeed = 1;
        }

        void updateForNewFrame() {
            trajectoryX = startX - targetX;
            trajectoryY = startY - targetY;
            trajectorySlope = trajectoryY / trajectoryX;

            if(!target.isExploding && target.isAlive) {
                if (currY > targetY || currX < 0 || currX > width) {
                    target.isExploding = true;
                } else {
                    currX += (1 / trajectorySlope) * missileSpeed;
                    currY += missileSpeed;
                }
            } else if (!target.isAlive && beehiveTargets.size() > 0) {
                startX = Math.random() * width;
                currX = startX;
                currY = startY;
                target = beehiveTargets.get((int) (Math.random() * beehiveTargets.size()));
                targetX = target.targetX;
            } else {
                currX = startX;
                currY = startY;
            }

            if(honeyMissile.isIgnited && Math.abs(currX - honeyMissile.currX) <= 0.75 * honeyMissile.ignitedCounter && Math.abs(currY - honeyMissile.currY)  <= 0.75 * honeyMissile.ignitedCounter) {
                score.currentScore++;
                startX = Math.random() * width;
                currX = startX;
                currY = startY;
                target = beehiveTargets.get((int) (Math.random() * beehiveTargets.size()));
                targetX = target.targetX;
            }
        }

        void draw(GraphicsContext g) {
            //Presentation Note: Replace color below with red for high contrast
            g.setLineWidth(3);
            g.setStroke(Color.FIREBRICK);
            g.strokeLine(startX, startY, currX, currY);
        }
    }

    private class HoneyMissile {
        double startX, startY, currX, currY;
        double trajectoryX, trajectoryY, trajectorySlope;
        boolean isFired, isIgnited;
        int ignitedCounter, missileSpeed;

        HoneyMissile() {
            isFired = false;
            isIgnited = false;
            startX = width / 2.0;
            startY = horizon;
            currX = startX;
            currY = startY;
            missileSpeed = 5;
        }
        void updateForNewFrame() {

            trajectoryX = startX - turret.endPointX;
            trajectoryY = startY - turret.endPointY;
            trajectorySlope = trajectoryY / trajectoryX;

            if(isFired) {
                if(currY < 0 || currX < 0 || currX > width) {
                    isFired = false;
                } else if(isIgnited) {
                    ignitedCounter++;
                    if(ignitedCounter == 35) {
                        ignitedCounter = 0;
                        isFired = false;
                        isIgnited = false;
                    }
                } else {
                    currX -= (1 / trajectorySlope) * missileSpeed;
                    currY -= missileSpeed;
                }
            }

        }

        void draw(GraphicsContext g) {
            //Presentation Note: Replace colors below with green for high contrast
            if(!isFired) {
                currX = startX;
                currY = startY;
            } else if(isIgnited) {
                g.setFill(Color.DARKGOLDENROD);
                g.fillOval(currX - 0.75 * ignitedCounter, currY - 0.75 * ignitedCounter, 1.5 * ignitedCounter, 1.5 * ignitedCounter);
            }
            g.setLineWidth(3);
            g.setStroke(Color.DARKGOLDENROD);
            g.strokeLine(startX, startY, currX, currY);
            g.setFill(Color.DARKGOLDENROD);
            g.fillOval(turret.centerX - 6.25, turret.centerY - 6.25, 12.5, 12.5);
        }
    }

    private class Score {
        int currentScore;

        Score() {
            currentScore = 0;
        }

        void draw(GraphicsContext g) {
            g.setFont(Font.font("Trebuchet", FontWeight.EXTRA_BOLD, 18));
            g.setFill(Color.BLACK);
            g.fillText("Current Score: " + currentScore, 470, height - 30);
        }
    }

    private class Title {
        Image titlePNG, lostPNG;
        boolean hasStart, hasLost;


        Title() {
            titlePNG = new Image("titleScreen.png"); //528 pixels by 119 pixels
            lostPNG = new Image("lostScreen.png"); //528 pixels by 184 pixels
            hasStart = false;
            hasLost = false;
        }

        void UpdateForNewFrame() {
            if(beehiveTargets.size() == 0) {
                hasLost = true;
            }
        }

        void draw(GraphicsContext g) {
            if(!hasStart) {
                g.drawImage(titlePNG, 56, height / 3.0);
            } else if(hasLost) {
                g.drawImage(lostPNG, 56, height / 3.0);
            }
        }
    }
}