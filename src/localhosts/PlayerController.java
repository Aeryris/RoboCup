package localhosts;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.github.robocup_atan.atan.model.ActionsPlayer;
import com.github.robocup_atan.atan.model.ControllerPlayer;
import com.github.robocup_atan.atan.model.enums.Errors;
import com.github.robocup_atan.atan.model.enums.Flag;
import com.github.robocup_atan.atan.model.enums.Line;
import com.github.robocup_atan.atan.model.enums.Ok;
import com.github.robocup_atan.atan.model.enums.PlayMode;
import com.github.robocup_atan.atan.model.enums.RefereeMessage;
import com.github.robocup_atan.atan.model.enums.ServerParams;
import com.github.robocup_atan.atan.model.enums.ViewAngle;
import com.github.robocup_atan.atan.model.enums.ViewQuality;
import com.github.robocup_atan.atan.model.enums.Warning;


/*
*
* task environments and properties:
* Partially-Observable, Multi agent, stochastic, sequential, dynamic, continuous
* Agent Type: Footballer
* Performance measure: scoring goals, defending their own goal against opponents
* Environment: Football pitch
* Actuators: kicking the ball, dashing, catching the ball (goalie), sending messages
* Sensors: the players eyes, hearing messages from their team mates.
* Agent program : Simple reflex
* It is a simple reflex program because the agents mostly communicate by checking if statements to see the state of
* the environment and the action they should take next
*
*/
public class PlayerController implements ControllerPlayer {
	
	private ActionsPlayer player;
	public String type;
	private static Logger log = Logger.getLogger(PlayerController.class);
	
	/**
	 * Team manager
	 */
	public static Team team = Team.getTeam();
	
	
	/**
	 * Direction
	 */
	private double ballDirection; //Ball direction 
	
	/**
	 * Distance
	 */
	private double ballDistance; //Ball distance
	private double ownGoalDistance; //Distance to own goal 
	private double oponentGoalDistance; //Distance to oppoent goal
	
	/**
	 * See
	 */
	private boolean canSeeOwnGoal; //See own goal
	private boolean canSeeOponentGoal; //See opponent goal
	private boolean canSeeGoal; //See any goal
	private boolean canSeeBall; //See ball
	private boolean canSeeNothing; //See nothing
	double canSeePlayerDirection; //Can see own player, return direction
	
	/**
	 * Before kickoff flag
	 */
	private boolean beforeKickOff = true;
	
	boolean canOpponentSide = true; //Can see opponent side
	
	double opponentSideDistance; //Distance to opponent side
	double opponentSideDirection; //Direction to opponent side
	
	boolean canSeeOwnSide = true; //See own side
	double ownSideDistance; //Distance to own side
	double ownSideDirection; //Direction to own side
	
	double opponentGoalDistance; //Distance to opponent goal 
	double ownPlayerDistance; //Distance to own player
	
	boolean hasTheBall; //If player had the ball
	 
	boolean heardMessageStartChase; //Head message

	double opponentGoalDirection;//Direction to opponents goal
	
	
	/**
	 * Stores ball informations for easy access
	 */
	SeeBall ballInfo = new SeeBall(); 
	
	int[] seeOwnPlayers; //Ids of own player, that this.player can see
	
	double closestOwnPlayerDistance = 1000; //Distance to the closest player
	int closestOwnPlayerId = 0; //Player id of the closest player
	double closestOwnPlayerDirection = 0; //Direction of the closest player
	
	int seeOwnPlayer = 0; //If player can see own player, id of that player is stored here 
	boolean bSeeOwnPlayer; //True if player can see own player
	boolean canSeeOwnPlayer = false; //True if player can see own player 
	
	int numberOfOwnPlayerSearches = 0; //How many times player searched for support
	
	public static int cycle = 0; //Number of cycles
	
	public static int lookAroundCount = 0; //How many times player looked around
	
	public boolean someoneFromOwnTeamHasBall; //True if the other team has the ball
	public int ownPlayerIdWithTheBall; //Player id with the ball 
	
	public boolean weHaveTheBall; //True if we have the ball 
	/*
	 * Variables for the defenders to query in the goal flags 
	 * they end up in temp variables so that the direction they are supposed to go in doesnt
	 * change into something else and have them stuck in the turnAndGoToGoal method
	 */
    private double closestOpponentDistance = 0;
    private double closestOpponentDirection = 0;
    private double otherPlayerDirection = 0;
    private boolean canSeeOtherPlayer = false;
    private double ownGoalDirection = 0;
    private double ownPenaltyDirection ;
    private double ownPenaltyDistance ;
    private double defendGoalDirection;
    private double defendGoalDistance;
    private double player11GoalDirection;
    private double player11GoalDistance;
    private double player10GoalDirection;
    private double player10GoalDistance;
    private double player9GoalDirection;
    private double player9GoalDistance;
    private double centerDistance;
    private double centerDirection;

	@Override
	public void preInfo() {
		this.setType("Player");
		
		this.ballDirection = 0;
		this.ballDistance = 1000;
		this.ownGoalDistance = 1000;
		this.oponentGoalDistance = 1000;
		
		this.canSeeGoal = false;
		this.canSeeOponentGoal = false;
		this.canSeeOwnGoal = false;
		this.canSeeBall = false;
		this.canSeeNothing = false;
		
		
	
	}

	@Override
	public void postInfo() {
		if (this.beforeKickOff)
			return;
		
		cycle++;
		//System.out.println("Cycle: -> " + cycle);

		try {
			int playerId = this.getPlayer().getNumber();
			
			/**
	            * If current player is defender perform following set of rules 
	            */
            if(team.get(playerId).getRole() == PlayerRole.Defender)
            {
                    if(
                                    getPlayer().getNumber() != 10
                                    ||
                                    getPlayer().getNumber() !=11
                       
                     || getPlayer().getNumber() != 9){
           
                    	/**
                    	 * If player can see the ball
                    	 */
                            if(canSeeBall)
                            {
                                    System.out.println("Defender" + "" + playerId + "" + "canSeeBall");
                                    
                                    /**
                                     * Try to get the ball
                                     */
                                    getPlayer().turn(ownGoalDirection);
                                    getPlayer().dash(100);
                                    //turnAndGoToGoal();
                                   
                                    /**
                                     * If the player has the ball
                                     */
                                    if(hasTheBall() && ownPlayerIdWithTheBall == getPlayer().getNumber()){
                                   
                                    System.out.println("HasTheBall");
                                    /**
                                     * If the player is on own side
                                     */
                                    if(isOnOwnSide()){
                                            System.out.println("isOnOwnSide");
                                            /**
                                             * If player is facing own goal
                                             */
                                            if(isFacingOwnGoal()){
                                            	/**
                                            	 * if player is facing own goal kick the ball in opposite direction
                                            	 */
                                                    getPlayer().kick(100, -ownSideDirection);
                                            }else{
                                            	/**
                                            	 * If player can see any supporting players
                                            	 */
                                                    if(seeOwnSupportPlayer()){
                                                    	/**
                                                    	 * If the ball is in a kickable distance
                                                    	 */
                                                            if(ballInKickableDistance()){
                                                            	/**
                                                            	 * Pass to the support player
                                                            	 */
                                                                    passBallToSupport();
                                                            }else{
                                                            	/**
                                                            	 * Kick in opponents side direction 
                                                            	 */
                                                                    getPlayer().kick(100,opponentSideDirection);
                                                            }
                                                    }else{
                                                    	/**
                                                    	 * Look around for support and pass
                                                    	 */
                                                            lookAroundAndPassToSupport();
                                                            /**
                                                             * Turn back to defend the goal
                                                             */
                                                            turnAndGoToGoal();
                                                    }
                                            }
                           
                    }
                                    /**
                                     * If player is on opponents die
                                     */
                                    else if(isOnOpponentSide()){
                                            System.out.println("isOnOpponentSide");
                                            /**
                                             * If player can see any supporting player
                                             */
                                            if(seeOwnSupportPlayer()){
                                                            System.out.println("seeOwnSupportPlayer");
                                                            if(ballInKickableDistance()){
                                                                    passBallToSupport();
                                                            }else{
                                                                    //dribbleInDirection(opponentSideDirection);
                                                                    getPlayer().kick(50, -opponentSideDirection);
                                                                    turnAndGoToGoal();
                                                            }
                                                    }else{
                                                    	/**
                                                    	 * Look around for supporting player and pass to the 
                                                    	 */
                                                            System.out.println("lookAroundAndPassToSupport");
                                                            lookAroundAndPassToSupport();
                                                            /**
                                                             * Go back to the defending position
                                                             */
                                                            turnAndGoToGoal();
                                                    }
                                                    //dribbleInDirection(opponentGoalDirection);
                                            }
                                           
                                           
                                    }
            }
                            else{
                                 
                            	/**
                            	 * Try to find anything useful, ball, or own player
                            	 */
                                    lookAround();
                                    /**
                                     * If can see own support player
                                     */
                                    if(seeOwnSupportPlayer()){
                                            System.out.println("seeOwnSupportPlayer");
                                            /**
                                             * Kick the ball to the support if the ball is in a kickable distance
                                             */
                                            if(ballInKickableDistance()){
                                            	/**
                                            	 * Pass the ball to the support
                                            	 */
                                                    passBallToSupport();
                                            }else{
                                            	/**
                                            	 * Go back to defent the goal
                                            	 */
                                                    turnAndGoToGoal();
                                            }
                                    }
                                   
                            }
                    }else  {
                    	/**
                    	 * Go back to defend the goal
                    	 */
                            turnAndGoToGoal();
                            /**
                             * Look around for attacks
                             */
                            lookAround();
           
                    }
                   
            }
                   
            /**
             * If current player is midfielder perform following set of rules 
             */
            else if(team.get(playerId).getRole() == PlayerRole.MidFielders
            && (getPlayer().getNumber() == 4 || getPlayer().getNumber() == 5)
            || getPlayer().getNumber() == 6)
            {

                    System.out.println("ownPlayerIdWithTheBall -> "+ ownPlayerIdWithTheBall + " -> " + getPlayer().getNumber());
   
           
                   /**
                    * If player can see the ball
                    */
                    if(canSeeBall){
                    	/**
                    	 * If the player has the ball
                    	 */
                            if(hasTheBall() && ownPlayerIdWithTheBall == getPlayer().getNumber()){
                                   
                                    System.out.println("hasTheBall");
                                    /**
                                     * If the player is on own side 
                                     */
                                    if(isOnOwnSide()){
                                            System.out.println("isOnOwnSide");
                                            /**
                                             * if the player is facing own goal
                                             */
                                            if(isFacingOwnGoal()){
                                            	/**
                                            	 * Kick in opposite direction if true
                                            	 */
                                                    getPlayer().kick(100, -ownSideDirection);
                                            }else{
                                            	/**
                                            	 * If not try to find support 
                                            	 */
                                                    if(seeOwnSupportPlayer()){
                                                    	/**
                                                    	 * If support is found check if the ball is in a kickable distance
                                                    	 */
                                                            if(ballInKickableDistance()){
                                                            	/**
                                                            	 * Pass to the support
                                                            	 */
                                                                    passBallToSupport();
                                                            }else{
                                                            	/**
                                                            	 * Dribble the ball to oppoent direction
                                                            	 */
                                                                    dribbleInDirection(opponentSideDirection);
                                                            }
                                                    }else{
                                                    	/**
                                                    	 * Find support team member and pass the ball
                                                    	 */
                                                            lookAroundAndPassToSupport();
                                                            /**
                                                             * Return 
                                                             */
                                                            returnToCenter();
                                                            lookAround();
                                                    }
                                            }
                                           
                                            /**
                                             * If the player is on opposite side
                                             */
                                    }else if(isOnOpponentSide()){
                                            System.out.println("isOnOpponentSide");
                                            /**
                                             * if the player is close to oppoent goal
                                             */
                                            if(isCloseToOpponentGoal()){
                                                    System.out.println("isCloseToOpponentGoal");
                                                    /**
                                                     * if the ball is in a kickable distance
                                                     */
                                                    if(ballInKickableDistance()){
                                                    	/**
                                                    	 * Try to score
                                                    	 */
                                                            kickInGoalDirection();
                                                    }else{
                                                           /**
                                                            * Find support team members
                                                            */
                                                            lookAroundAndPassToSupport();
                                                            System.out.println("RunForBall");
                                                            /**
                                                             * Run for the ball
                                                             */
                                                            runForBall();
                                                    }
                                                   
                                            }else{
                                            	/**
                                            	 * If can see own supporting player
                                            	 */
                                                    if(seeOwnSupportPlayer()){
                                                            System.out.println("seeOwnSupportPlayer");
                                                            /**
                                                             * If ball if in a kickable distance
                                                             */
                                                            if(ballInKickableDistance()){
                                                            	/**
                                                            	 * Pass to support player if true
                                                            	 */
                                                                    passBallToSupport();
                                                            }else{
                                                            	/**
                                                            	 * Dribble in opponents side direction
                                                            	 */
                                                                    dribbleInDirection(opponentSideDirection);
                                                            }
                                                    }else{
                                                            System.out.println("lookAroundAndPassToSupport");
                                                            /**
                                                             * Look around for support and pass
                                                             */
                                                            lookAroundAndPassToSupport();
                                                           
                                                    }
                                                    //dribbleInDirection(opponentGoalDirection);
                                            }
                                           
                                           
                                    }else{
                                            System.out.println("Else");
                                            /**
                                             * If can see own support player
                                             */
                                            if(seeOwnSupportPlayer()){
                                                    System.out.println("seeOwnSupportPlayer");
                                                    /**
                                                     * Check if can kick the ball
                                                     */
                                                    if(ballInKickableDistance()){
                                                    	/**
                                                    	 * Pass the ball to the team member
                                                    	 */
                                                            passBallToSupport();
                                                    }else{
                                                    	/**
                                                    	 * Dirbble in opponents direction
                                                    	 */
                                                            dribbleInDirection(opponentSideDirection);
                                                    }
                                            }else{
                                                    System.out.println("lookAroundAndPassToSupport");
                                                    /**
                                                     * Look for support and pass
                                                     */
                                                    lookAroundAndPassToSupport();
                                                   
                                            }
                                    }
                                   
                                   
                                   
                            }else{
                                  
                                    System.out.println("No Ball -> runBall");
                                    /**
                                     * Get the ball
                                     */
                                    runForBall();
                            }
                           
                           
                           
                    }else{
                           
                    	/**
                    	 * Return to initial position 
                    	 */
                            returnToCenter();
                            
                            /**
                             * Look around
                             */
                            lookAround();
                            /**
                             * Try to get the ball if the ball is in sight
                             */
                            findTheBallAndGetIt();
                           
                           
                           
                           
                           
                    }
            }
             
           /**
            * If current player is attacker perform following set of rules 
            */
            else if(team.get(playerId).getRole() == PlayerRole.Attacker){
				
				
				System.out.println("ownPlayerIdWithTheBall -> "+ ownPlayerIdWithTheBall + " -> " + getPlayer().getNumber());
				
				
				/**
				 * Check if player can see the ball
				 */
				if(canSeeBall){

					/**
					 * Check if player has the ball, otherwise look and run for it 
					 */
					if(hasTheBall()){
						
						System.out.println("hasTheBall");
						/**
						 * Check if the player is on own side of the pitch 
						 */
						if(isOnOwnSide()){
							System.out.println("isOnOwnSide");
							/**
							 * Check if player is facing own goal, if yes kick in opposite direction
							 */
							if(isFacingOwnGoal()){
								getPlayer().kick(100, -ownSideDirection);
							}else{
								/**
								 * Check if player can see any support from own team
								 */
								if(seeOwnSupportPlayer()){
									/**
									 * Check if ball is in a kickable distance, if yes, pass it to the support player
									 * if not dribble in opponent side direction
									 */
									if(ballInKickableDistance()){
										/**
										 * Pass to closest support player
										 */
										passBallToSupport();
									}else{
										/**
										 * Dribble to opponents side
										 */
										dribbleInDirection(opponentSideDirection);
									}
								}else{
									/**
									 * Find support player and pass
									 */
									lookAroundAndPassToSupport();
								}
							}
							
						/**
						 * Check if player is on the opponent side 	
						 */
						}else if(isOnOpponentSide()){
							System.out.println("isOnOpponentSide");
							/**
							 * Check if player is close to the opponent goal 
							 */
							if(isCloseToOpponentGoal()){
								System.out.println("isCloseToOpponentGoal");
								/**
								 * Check if ball is in a kickable distance, if yes try to score, if not find support player and pass
								 * and run towards the ball
								 */
								if(ballInKickableDistance()){
									kickInGoalDirection();
								}else{
									/**
									 * Look for support player
									 */
									lookAroundAndPassToSupport();
									System.out.println("RunForBall");
									/**
									 * Run for the ball
									 */
									runForBall();
								}
								
							}else{
								/**
								 * Check if player can see support player 
								 */
								if(seeOwnSupportPlayer()){
									System.out.println("seeOwnSupportPlayer");
									/**
									 * If ball is in a kickable distance pass the ball to the support 
									 */
									if(ballInKickableDistance()){
										/**
										 * Pass to the support player
										 */
										passBallToSupport();
									}else{
										/**
										 * Otherwise dribble on opponents side direction
										 */
										dribbleInDirection(opponentSideDirection);
									}
								/**
								 * Look around for support player	
								 */
								}else{
									System.out.println("lookAroundAndPassToSupport");
									/**
									 * Look around and pass to support player
									 */
									lookAroundAndPassToSupport();
									
								}
								//dribbleInDirection(opponentGoalDirection);
							}
							
							
						}else{
							/**
							 * Check if player can see support player 
							 */
							if(seeOwnSupportPlayer()){
								System.out.println("seeOwnSupportPlayer");
								/**
								 * If ball is in a kickable distance pass the ball to the support 
								 */
								if(ballInKickableDistance()){
									/**
									 * Pass to the support player
									 */
									passBallToSupport();
								}else{
									/**
									 * Otherwise dribble on opponents side direction
									 */
									dribbleInDirection(opponentSideDirection);
								}
								/**
								 * Look around for support player	
								 */	
							}else{
								System.out.println("lookAroundAndPassToSupport");
								/**
								 * Look around and pass to support player
								 */
								lookAroundAndPassToSupport();
								
							}
						}
						
						
						
					}else{
						
						
						/**
						 * Try to get the ball
						 */
						System.out.println("No ball -> runForBall");
						runForBall();
					}
					
					
					
				}else{
					/**
					 * Look around for the ball
					 */
					lookAround();
					
					
					
					
				}
			}else if(getPlayer().getNumber() == 8 || getPlayer().getNumber() == 7){
				
				
				//runInDirection(opponentGoalDirection);
				if(weHaveTheBall){
					supportPlayerWithBall();
					
				}
				
			}
			
			
			

		} catch (Exception e) {

		}

	}
	
	/**
	 * If own player has the ball, run towards opponents goal to support him
	 */
	public void supportPlayerWithBall(){
		getPlayer().turn(opponentSideDirection);
		getPlayer().dash(30);
	}
	
	/**
	 * Check whether own player is facing own goal
	 * @return
	 */
	public boolean isFacingOwnGoal(){
		if(canSeeOwnGoal){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Look around for supporting own player for 2 times, if distance between own support player is greater than 10, pass if not dribble towards opponents goal
	 */
	public void lookAroundAndPassToSupport(){
		System.out.println("lookAroundCount -> " + lookAroundCount);
		if(lookAroundCount < 2){
			lookForSupport();
		}else{
			
			if(ownPlayerDistance > 10){
				passBallToSupport();
			}else{
				dribbleInDirection(opponentGoalDirection);
			}
			
			lookAroundCount = 0;
		}
	}
	
	/**
	 * Kick the ball in the opponents goal direction
	 */
	public void kickInGoalDirection(){
		System.out.println("kickInGoalDirection -> " + opponentGoalDirection);
		//getPlayer().turn(opponentGoalDirection);
		getPlayer().kick(100, opponentGoalDirection);
	}
	
	/**
	 * Pass the ball to the own support player
	 */
	public void passBallToSupport(){
		getPlayer().turn(canSeePlayerDirection);
		getPlayer().kick(100, canSeePlayerDirection);
	}
	
	/**
	 * Check whether player is close to the goal ( < 15 ), is yes return true
	 * @return boolean
	 */
	public boolean shouldTryToScoreGoal(){
		if(canOpponentSide && opponentGoalDistance < 15){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if player is close to the opponents goal
	 * @return boolean
	 */
	public boolean isCloseToOpponentGoal(){
		if(canOpponentSide && opponentGoalDistance < 20){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks whether is close to own goal 
	 * @return boolean
	 */
	public boolean isCloseToOwnGoal(){
		if(canSeeOwnSide && ownGoalDistance < 25){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks whether player is on own side of the pitch, (if can see own side and the distance is < 50)
	 * @return boolean
	 */
	public boolean isOnOwnSide(){
		if(canSeeOwnSide && ownSideDistance < 50){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks whether player is on opponents side of the pitch, (if can see opponent side and the distance is < 50)
	 * @return boolean
	 */
	public boolean isOnOpponentSide(){
		if(canOpponentSide && opponentSideDistance < 50){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether player has the ball ( distance to the ball is <= 1.9)
	 * @return
	 */
	public boolean hasTheBall(){
		if(ballDistance <= 1.9){
			ownPlayerIdWithTheBall = getPlayer().getNumber();
			weHaveTheBall = true;
			return true;
		}
		weHaveTheBall = false;
		ownPlayerIdWithTheBall = 0;
		return false;
	}
	
	/**
	 * Look around to try to find the ball, if player can see it, run for the ball
	 */
	public void findTheBallAndGetIt(){
		if(canSeeBall){
			runForBall();
		}else{
			lookAround();
		}
	}
	
	/**
	 * Look around
	 */
	public void lookAround(){
		getPlayer().turn(40);
		lookAroundCount++;
	}
	
	/**
	 * Dash in the ball direction
	 */
	public void runForBall(){
		System.out.println("BallDistance -> " + ballDistance);
		//if(ballDistance > 2){
			getPlayer().turn(ballDirection);
			getPlayer().dash(100);
		//}
	}
	
	/**
	 * Checks whether player can see opponent side of the pitch, (if can see opponent side and the distance is > 45)
	 * @return boolean
	 */
	public boolean seeOpponentSide(){
		if (canOpponentSide && opponentGoalDistance > 45){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether player is on own side of the pitch, (if can see own side and the distance is < 50)
	 * @return boolean
	 */
	public boolean seeOwnSide() {
		if (canSeeOwnSide && ownGoalDistance < 50) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check whether player can see any own player 
	 * @return boolean
	 */
	public boolean seeOwnSupportPlayer(){
		if(bSeeOwnPlayer){
			
			return true;
		}
		return false;
	}
	
	/**
	 * Look around for support player
	 */
	public void lookForSupport(){
		if(!bSeeOwnPlayer){
			numberOfOwnPlayerSearches++;
			lookAroundCount++;
		}else{
			getPlayer().turn(40);
		}
	}
	
	/**
	 * Run in the given direction
	 * @param direction
	 */
	public void runInDirection(double direction){
		getPlayer().turn(direction);
		getPlayer().dash(100);
	}
	
	/**
	 * Slowly dribble in the given direction 
	 * @param direction
	 */
	public void dribbleInDirection(double direction){
		getPlayer().turn(direction);
		getPlayer().kick(40, direction);
		getPlayer().dash(60);
	}
	
	/**
	 * Check whether the ball is in a kick able distance
	 * @return boolean
	 */
	public boolean ballInKickableDistance(){
		System.out.println("ballInKickableDistance:  -> " +ballDistance);
		if(ballDistance <= 1){
			ownPlayerIdWithTheBall = getPlayer().getNumber();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Look for the player with number
	 * @param seePlayerId
	 * @param playerToLookFor
	 * @param whoShouldLook
	 * @return
	 */
	public double lookForPlayer(int seePlayerId, int playerToLookFor, int whoShouldLook){
		System.out.println("LookForPlayer: " + seePlayerId + " -> " + playerToLookFor + " -> " + whoShouldLook);
		if(getPlayer().getNumber() == whoShouldLook){
			if(seePlayerId == playerToLookFor){
				return 1;
			}else{
				System.out.println("Cant see: "+playerToLookFor + " so turn" );
				getPlayer().turn(45);
			}
		}
		
		return 0;
	}
	
	/**
	 * Try to get the ball
	 */
	public void getTheBall(){
		turnTowardsBall();
		getPlayer().dash(100);
	}
	
	public void kickBall(int playerId){
		if(ballInfo.distance < 5 && ballInfo.distance != 0.0){
			//getPlayer().kick(100, -180);
			//System.out.println("KickBall "+ playerId);
		}
	}
	
	//Used by midfielders to run around the center area based on the center flag
    
    private void returnToCenter()
    {
            lookAround();
            getPlayer().turn(centerDirection);
            getPlayer().dash(60);
            if(isOnOwnSide()){
                    getPlayer().turn(opponentSideDirection);
            }
            runForBall();
    }
    // for the defenders to mark their goal area
    /*
     * they check where the goal area is and run to it, if they see a player from another team with the
     * ball they attempt to get it and defend their area
     */
    private void turnAndGoToGoal()
    {

            //lookAround();

            double temp1 = player9GoalDistance;
            double temp2 = player10GoalDistance;
            double temp3 = player11GoalDistance;
            if(getPlayer().getNumber() == 9){
                    getPlayer().turn(temp1);
                    getPlayer().dash(100);
                    if(isCloseToOwnGoal()){
                            getPlayer().turn(opponentSideDirection);
                    }
                   
                   
            }
            else if(getPlayer().getNumber() == 10){
                    getPlayer().turn(temp2);
                    getPlayer().dash(100);
                    if(isCloseToOwnGoal()){
                            getPlayer().turn(opponentSideDirection);
                    }
                   
            }
            else if(getPlayer().getNumber() == 11){
                    getPlayer().turn(temp3);
                    getPlayer().dash(100);
                    if(isCloseToOwnGoal()){
                            getPlayer().turn(opponentSideDirection);
                    }
            }

            if((isCloseToOwnGoal()) && (canSeeBall) && (canSeeOtherPlayer))
            {
                    defendGoalAction();
            }
           
            getPlayer().turn(opponentSideDirection);
   
           
           
    }
   
    //defenders can use this to get the ball away from their area
   
    private void defendGoalAction() {
    getPlayer().turn(ballDirection);
    int tempDash = (int) ballDirection;
    getPlayer().dash(tempDash);
    if (ballInKickableDistance()) {
        getPlayer().kick(100, (opponentSideDirection));            
    }

}
	
	/**
	 * Kick ball towards given direction
	 * @param playerId
	 * @param direction
	 * @param power
	 */
	public void kickTowards(int playerId, double direction, int power){
		if(ballInfo.distance < 0.7 && ballInfo.distance != 0.0){
			getPlayer().kick(power, direction);
			System.out.println("KickTowards "+ playerId + " dir: " + direction + " power: " + power);
		}
	}
	
	/**
	 * Turn towards the ball direction
	 */
	public void turnTowardsBall(){
		System.out.println("TurnTowardsBall: " + ballInfo.direction);
		getPlayer().turn(ballInfo.direction);
	}
	
	@Override
	public ActionsPlayer getPlayer() {
		return this.player;
	}

	@Override
	public void setPlayer(ActionsPlayer c) {
		this.player = c;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String newType) {
		this.type = newType;
	}

	@Override
	public void infoSeeFlagRight(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagLeft(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		canSeeOwnSide = true;
		canOpponentSide = false;
		ownSideDistance = distance;
		ownSideDirection = direction;
	}

	@Override
	public void infoSeeFlagOther(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
		canOpponentSide = true;
		canSeeOwnSide = false;
		
		
		opponentSideDirection = direction;
		opponentSideDistance = distance;
		
		
	}

	@Override
	public void infoSeeFlagCenter(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		//queried by the midfielders
        if(getPlayer().getNumber() == 4
                        ||
                        getPlayer().getNumber() == 5
                        ||
                        getPlayer().getNumber() == 6){
        canOpponentSide = true;
        canSeeOwnSide = true;
        centerDistance = distance;
        centerDirection = direction;
        }
	}

	@Override
	public void infoSeeFlagCornerOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagCornerOther(Flag flag, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagPenaltyOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		//for the defenders
        if(getPlayer().getNumber() == 9
                        ||
                        getPlayer().getNumber() == 10
                        ||
                        getPlayer().getNumber() == 11){
        canSeeOwnSide = true;
        canSeeGoal = true;
        canOpponentSide = false;
        ownPenaltyDistance = distance;
        ownPenaltyDirection  = direction;
        }
	}

	@Override
	public void infoSeeFlagPenaltyOther(Flag flag, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagGoalOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		canSeeOwnSide = true;
        canSeeGoal = true;
        canOpponentSide = false;
        ownGoalDistance = distance;
        ownGoalDirection  = direction;

		if(getPlayer().getNumber() == 9)
        {
                player9GoalDistance = distance;
                player9GoalDirection = direction;
        }
        else if(getPlayer().getNumber() == 10)
        {
                player10GoalDistance = distance;
                player10GoalDirection = direction;
        }
        else if(getPlayer().getNumber() == 11)
        {
                player11GoalDistance = distance;
                player11GoalDirection = direction;
        }
	}

	@Override
	public void infoSeeFlagGoalOther(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		//team.get(this.getPlayer().getNumber()).getController().
		canOpponentSide = true;
		canSeeOwnSide = false;
		opponentGoalDistance = distance;
		opponentGoalDirection = direction;
	}

	@Override
	public void infoSeeLine(Line line, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeePlayerOther(int number, boolean goalie, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		System.out.println("OtherPlayer: " + number);
		System.out.println("OtherPlayer: " + number);
	       
        if (distance < closestOpponentDistance )
        {
                closestOpponentDistance = distance;
                closestOpponentDirection = direction;
        }
       
        otherPlayerDirection = direction;
        canSeeOtherPlayer = true;
	}

	@Override
	public void infoSeePlayerOwn(int number, boolean goalie, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub

		if(distance < closestOwnPlayerDistance){
			closestOwnPlayerDistance = distance;
			closestOwnPlayerId = number;
			closestOwnPlayerDirection = direction;
			
		}
		
		seeOwnPlayer = number;
		canSeePlayerDirection = direction;
		canSeeOwnPlayer = true;
		ownPlayerDistance = distance;

		int playerId = getPlayer().getNumber();

		/**
		 * [*]
		 * [1]
		 * [2] -> findBall() ? LookAroud (canSeeBall = true) : AskWhoCanSeeTheBall()->6->chasePlayerId(6)
		 * [3]
		 * [4]
		 * [5]
		 * [6] canSeeBall = true
		 * [7]
		 * [8]
		 * [9]
		 * [10]
		 */
				
	}

	@Override
	public void infoSeeBall(double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub

		
		canSeeBall = true;
		ballDirection = direction;
		ballDistance = distance;
		
		
		
		int playerId = this.getPlayer().getNumber();
		
		
		SeeBall ball = new SeeBall();
		
		ball.distance = distance;
		ball.direction = direction;
		ball.distChange = distChange;
		ball.dirChange = dirChange;
		ball.bodyFacingDirection = bodyFacingDirection;
		ball.headFacingDirection = headFacingDirection;
		team.get(playerId).setSeeBall(ball);
		
		
	}

	@Override
	public void infoHearReferee(RefereeMessage refereeMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearPlayMode(PlayMode playMode) {
		this.pause(1000);
		
		if(playMode == PlayMode.KICK_OFF_L
				||
		   playMode == PlayMode.KICK_OFF_R 
		        ||
		   playMode == PlayMode.KICK_OFF_OWN 
		   		||
		   playMode == PlayMode.KICK_OFF_OTHER){
			this.beforeKickOff = false;
		}
		
		int playerId = this.getPlayer().getNumber();
		
		/**
		 * Create team member
		 */
		Player player = new Player(this, playerId);
		
		/**
		 * Add player to the team
		 */
		team.addTeamMember(playerId, player);
		
		/**
		 * Set player role
		 */
		team.autoAssignPlayerRole(playerId);
		
		/**
		 * Position players on the pitch
		 */
		//PositionPlayers positionPlayers = new PositionPlayers(this, playMode, this.getPlayer().getNumber());
		Formation formation = new Formation(playerId, playMode);
		
		if(playerId == 2){
			getPlayer().turn(-15);
		}
		if(playerId == 3){
			getPlayer().turn(15);
		}
		
		
	}

	@Override
	public void infoHearPlayer(double direction, String message) {
		// TODO Auto-generated method stub
		
		if(message.equals("p9"))
			heardMessageStartChase = true;
		
	}

	@Override
	public void infoHearError(Errors error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearOk(Ok ok) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearWarning(Warning warning) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSenseBody(ViewQuality viewQuality, ViewAngle viewAngle, double stamina, double unknown,
			double effort, double speedAmount, double speedDirection, double headAngle, int kickCount, int dashCount,
			int turnCount, int sayCount, int turnNeckCount, int catchCount, int moveCount, int changeViewCount) {
		// TODO Auto-generated method stub
		
		try{
			SenseBody sense = new SenseBody();
			sense.viewQuality = viewQuality;
			sense.viewAngle = viewAngle;
			sense.stamina = stamina;
			sense.unknown = unknown;
			sense.effort = effort;
			sense.speedAmount = speedAmount;
			sense.speedDirection = speedDirection;
			sense.headAngle = headAngle;
			sense.kickCount = kickCount;
			sense.dashCount = dashCount;
			sense.turnCount = turnCount;
			sense.sayCount = sayCount;
			sense.turnNeckCount = turnNeckCount;
			sense.catchCount = catchCount;
			sense.moveCount = moveCount;
			sense.changeViewCount = changeViewCount;
			
			int playerId = this.getPlayer().getNumber();
			team.get(playerId).setSenseBody(sense);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		
		
	}

	@Override
	public void infoCPTOwn(int unum, int type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoCPTOther(int unum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoPlayerType(int id, double playerSpeedMax, double staminaIncMax, double playerDecay,
			double inertiaMoment, double dashPowerRate, double playerSize, double kickableMargin, double kickRand,
			double extraStamina, double effortMax, double effortMin) {
		// TODO Auto-generated method stub
		
		PlayerType type = new PlayerType();
		type.id = id;
		type.playerSpeedMax = playerSpeedMax;
		type.staminaIncMax = staminaIncMax;
		type.playerDecay = playerDecay;
		type.inertiaMoment = inertiaMoment;
		type.dashPowerRate = dashPowerRate;
		type.playerSize = playerSize;
		type.kickableMargin = kickableMargin;
		type.kickRand = kickRand;
		type.extraStamina = extraStamina;
		type.effortMax = effortMax;
		type.effortMin = effortMin;
		
		int playerId = this.getPlayer().getNumber();
		
		team.get(playerId).setPlayerType(type);
		
		
	}

	@Override
	public void infoPlayerParam(double allowMultDefaultType, double dashPowerRateDeltaMax, double dashPowerRateDeltaMin,
			double effortMaxDeltaFactor, double effortMinDeltaFactor, double extraStaminaDeltaMax,
			double extraStaminaDeltaMin, double inertiaMomentDeltaFactor, double kickRandDeltaFactor,
			double kickableMarginDeltaMax, double kickableMarginDeltaMin, double newDashPowerRateDeltaMax,
			double newDashPowerRateDeltaMin, double newStaminaIncMaxDeltaFactor, double playerDecayDeltaMax,
			double playerDecayDeltaMin, double playerTypes, double ptMax, double randomSeed,
			double staminaIncMaxDeltaFactor, double subsMax) {
		// TODO Auto-generated method stub
		
		PlayerParam param = new PlayerParam();
		param.allowMultDefaultType = allowMultDefaultType;
		param.dashPowerRateDeltaMax = dashPowerRateDeltaMax;
		param.dashPowerRateDeltaMin = dashPowerRateDeltaMin;
		param.effortMaxDeltaFactor =  effortMaxDeltaFactor;
		param.effortMinDeltaFactor = effortMinDeltaFactor;
		param.extraStaminaDeltaMax = extraStaminaDeltaMax;
		param.extraStaminaDeltaMin = extraStaminaDeltaMin;
		param.inertiaMomentDeltaFactor = inertiaMomentDeltaFactor;
		param.kickRandDeltaFactor = kickRandDeltaFactor;
		param.kickableMarginDeltaMax = kickableMarginDeltaMax;
		param.kickableMarginDeltaMin = kickableMarginDeltaMin;
		param.newDashPowerRateDeltaMax = newDashPowerRateDeltaMax;
		param.newDashPowerRateDeltaMin = newDashPowerRateDeltaMin;
		param.newStaminaIncMaxDeltaFactor = newStaminaIncMaxDeltaFactor;
		param.playerDecayDeltaMax = playerDecayDeltaMax;
		param.playerDecayDeltaMin = playerDecayDeltaMin;
		param.playerTypes = playerTypes;
		param.ptMax = ptMax;
		param.randomSeed = randomSeed;
		param.staminaIncMaxDeltaFactor = staminaIncMaxDeltaFactor;
		param.subsMax = subsMax;
		
		int playerId = this.getPlayer().getNumber();
		
		team.get(playerId).setPlayerParam(param);
		
		
	}

	@Override
	public void infoServerParam(HashMap<ServerParams, Object> info) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	/**
     * Pause the thread.
     * @param ms How long to pause the thread for (in ms).
     */
    private synchronized void pause(int ms) {
        try {
            this.wait(ms);
        } catch (InterruptedException ex) {
            log.warn("Interrupted Exception ", ex);
        }
    }
    
    

}
