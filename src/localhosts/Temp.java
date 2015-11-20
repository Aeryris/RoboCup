package localhosts;

import com.github.robocup_atan.atan.model.enums.ViewAngle;
import com.github.robocup_atan.atan.model.enums.ViewQuality;

public class Temp {
//	if (playerId == 9) {
//
//		// System.out.println("HeardMessage " + heardMessageStartChase);
//		if (!heardMessageStartChase) {
//
//			if (canSeeBall) {
//
//				/**
//				 * if(ballDistance > 4){
//				 * getPlayer().turn(ballDirection);
//				 * getPlayer().dash(100); }
//				 */
//
//				if (playerId == 9) {
//					System.out.println("BallDistance -> " + ballDistance);
//					System.out.println("seeOwnPlayer -> " + seeOwnPlayer);
//					System.out.println("canSeePlayerDirection -> " + canSeePlayerDirection);
//					System.out.println("ownPlayerDistance -> " + ownPlayerDistance);
//
//					System.out.println("CanSeeOwnSide: " + canSeeOwnSide);
//					System.out.println("CanSeeOpponentSide: " + canOpponentSide);
//
//					System.out.println("OwnSideDist: " + ownSideDistance);
//					System.out.println("OpponentSideDist: " + opponentSideDistance);
//
//					System.out.println("OwnSideDirection: " + ownSideDirection);
//					System.out.println("OpponentSideDir: " + opponentSideDirection);
//				}
//
//				if (ballDistance <= 4) {
//					System.out.println("BallInKickableDistance");
//					if (seeOwnPlayer == 7 || seeOwnPlayer == 2 || seeOwnPlayer == 3 || seeOwnPlayer == 6) {
//						System.out.println("CanSee 7, 2, 3, 6");
//						/**
//						 * Searches for player -> but kick in the wrong
//						 * direction
//						 */
//
//						getPlayer().turn(canSeePlayerDirection);
//						getPlayer().kick(100, 0);
//
//						System.out.println("Kick");
//					} else {
//						System.out.println("Cannot find support player");
//
//						/**
//						 * If he cannot see the player Depending on
//						 * where the player who has the ball is if
//						 */
//						// getPlayer().changeViewMode(ViewQuality.HIGH,
//						// ViewAngle.NARROW);
//						// getPlayer().kick(10, canSeePlayerDirection);
//						// getPlayer().turn(30);
//
//						// if he cannot find own player, kick (10)
//						// towards opponent flag and dash
//						//
//
//						// if(canSeeOwnSide)
//
//						/**
//						 * Add search for player
//						 */
//						if (canSeeOwnSide && ownSideDistance < 50) {
//							System.out.println("CanSeeOwnSide && OwnSideDistance");
//							// Kick it -> ownSideDirection
//							if (ballDistance <= 0.7) {
//
//								if (seeOwnPlayer == 7 || seeOwnPlayer == 2 || seeOwnPlayer == 3
//										|| seeOwnPlayer == 6) {
//
//									getPlayer().kick(100, canSeePlayerDirection);
//
//								} else {
//									getPlayer().turn(40);
//									getPlayer().kick(100, opponentSideDirection);
//									getPlayer().dash(100);
//								}
//
//							} else {
//								getPlayer().turn(ballDirection);
//								getPlayer().dash(10);
//							}
//
//						} else {
//							System.out.println("KickKick");
//							if (ownSideDirection != 0.0 && ownSideDistance != 0.0) {
//								if (ballDistance <= 0.7) {
//									getPlayer().kick(10, ownSideDirection);
//								} else {
//									getPlayer().turn(ballDirection);
//									getPlayer().dash(100);
//								}
//
//							} else {
//								if (ballDistance <= 0.7) {
//									getPlayer().kick(10, opponentSideDirection);
//								} else {
//									getPlayer().turn(ballDirection);
//									getPlayer().dash(100);
//								}
//							}
//
//						}
//
//						/*
//						 * if(canSeeOwnSide && ownSideDistance >
//						 * opponentSideDistance){ getPlayer().kick(100,
//						 * ownSideDirection); }else{
//						 * getPlayer().kick(10, ownSideDirection);
//						 * getPlayer().turn(ownSideDirection);
//						 * getPlayer().dash(10); }
//						 * 
//						 * if(canOpponentSide && opponentSideDistance <
//						 * 50){ getPlayer().kick(10,
//						 * opponentSideDirection);
//						 * getPlayer().turn(opponentSideDirection);
//						 * getPlayer().dash(10); }else{
//						 * 
//						 * }
//						 * 
//						 * if(canOpponentSide && opponentSideDistance >
//						 * ownSideDistance){ getPlayer().kick(10,
//						 * opponentSideDirection);
//						 * getPlayer().turn(opponentSideDirection);
//						 * getPlayer().dash(10); }else{
//						 * 
//						 * }
//						 */
//
//						if (canSeeOwnSide) {
//							// getPlayer().kick(100, 0);
//						}
//
//					}
//				}
//
//			} else {
//
//				/**
//				 * if(canSeeOwnSide && ownSideDistance < 50){ //Kick it
//				 * -> ownSideDirection if(ballDistance <= 0.7){
//				 * getPlayer().kick(100, ownSideDirection); }else{
//				 * getPlayer().turn(ballDirection);
//				 * getPlayer().dash(10); }
//				 * 
//				 * }else{ getPlayer().kick(10, ownSideDirection);
//				 * getPlayer().turn(ownSideDirection);
//				 * getPlayer().dash(10); }
//				 */
//
//				getPlayer().turn(40);
//				getPlayer().changeViewMode(ViewQuality.HIGH, ViewAngle.NORMAL);
//			}
//
//		}
//
//	}
//
//	if (playerId == 4) { // || playerId == 3 || playerId == 2){
//							// //MidAttackers
//
//		if (canSeeOwnSide && ownGoalDistance < 50 && ballDistance < 1) {
//
//			// System.out.println("Closest player: "+
//			// closestOwnPlayerId);
//
//			if (seeOwnPlayer == 8) {
//				getPlayer().turn(canSeePlayerDirection);
//			} else {
//				getPlayer().turn(20);
//			}
//
//		}
//
//		/**
//		 * If opponent goal distance is > 45 and can see opponents side,
//		 * turn towards our side and pass to own player
//		 */
//		if (canOpponentSide && opponentGoalDistance > 45 && ballDistance < 1) { // canSeeOppositeSide
//																				// &&
//																				// Distance
//																				// to
//																				// the
//																				// goal
//																				// is
//																				// >
//			getPlayer().turn(-180);
//
//			// infoSeePlayerOwn -> direction -> kick
//
//			canOpponentSide = false;
//			oponentGoalDistance = -1;
//
//		}
//
//		if (canSeeBall) {
//			// System.out.println("Player: " + playerId + " ->
//			// canSeeBall -> " + ballDistance);
//
//			// Turn towards ballDirection
//			// Dash towards ballDirection
//
//			getPlayer().turn(ballDirection);
//			getPlayer().dash(100);
//
//			if (ballDistance < 5) {
//				// Kick
//				// getPlayer().turn(getPlayer());
//				getPlayer().kick(100, -180);
//				heardMessageStartChase = true;
//				// getPlayer().say("p9");
//
//			}
//
//		}
//
//	}
}
