package Views; 

import engine.Game;  
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.control.Label;

public class GameGui extends Application {
	
	Stage window;
	Scene mainMenu;
	Scene chooseType, Fighters, Medics, Explorers;
	Scene gameMap;
	Scene Lose;
	Scene Win;
	static Button mapButtons[][] = new Button[15][15];
	static Hero chosenHero;
	static GridPane mapGrid = new GridPane();
	int whichStarter;
	
	public static void setAllInvisible() {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				mapGrid.getChildren().remove(mapButtons[i][j]);
				
			}
		}
	}
	
	public static void setIfVisible() {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				if(Game.map[i][j].isVisible() == true) {
					if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell)(Game.map[i][j])).getCharacter() != null) {
						mapButtons[i][j] = new Button(((CharacterCell)(Game.map[i][j])).getCharacter().getName());
						Character a = ((CharacterCell)(Game.map[i][j])).getCharacter();
						String type;
						mapButtons[i][j].setOnMouseClicked(event -> {
							if(event.getClickCount() == 1 && chosenHero != null) 
								chosenHero.setTarget(a);
						});
						
						if(((CharacterCell)(Game.map[i][j])).getCharacter() instanceof Hero) {
							Hero aHero = (Hero)(a);
							if(aHero instanceof Fighter)
								type = "Fighter";
							else if(aHero instanceof Explorer)
								type = "Explorer";
							else
								type = "Medic";
							mapButtons[i][j] = new Button("Name: " + aHero.getName() + "\n" +"Type: "+ type +"\n" +
						    "HP: " + aHero.getCurrentHp() + "\n" + "Attack damage: " + aHero.getAttackDmg() +"\n"+
									"Max actions:" + aHero.getMaxActions());
							
							mapButtons[i][j].setOnMouseClicked(event -> {
								if(event.getClickCount() == 1 && chosenHero != null) 
									chosenHero.setTarget(a);
								
								else if(event.getClickCount() == 2){
								    chosenHero = aHero;
								    Popups.popUps("Stats", "Name: " + chosenHero.getName() +"\n"+ "Type: " + type + "\n" +
								    "HP: "+ chosenHero.getCurrentHp() +"\n"+ "Attack damage: " + chosenHero.getAttackDmg() +"\n"+
								    "Remaining action points: " + chosenHero.getActionsAvailable()  +"\n"+
								    "Supplies with this hero: "+ chosenHero.getSupplyInventory().size() +"\n"+
								    "Vaccines with this hero: "+ chosenHero.getVaccineInventory().size());
								}
							});
						}
					 }
					
					else if(Game.map[i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(Game.map[i][j])).getCollectible() instanceof Vaccine)
							mapButtons[i][j] = new Button("Vaccine");
						else
							mapButtons[i][j] = new Button("Supply ");
					}
					
					else
						mapButtons[i][j] = new Button("       ");
				}
				else if(Game.map[i][j].isVisible() == false)
					mapButtons[i][j] = new Button("       ");
				
				GridPane.setConstraints(mapButtons[i][j], i, 14-j);
				mapGrid.getChildren().add(mapButtons[i][j]);
			}
		}
		
	}
	
	public static void setAllVisible() {
		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				
					if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell)(Game.map[i][j])).getCharacter() != null) {
						mapButtons[i][j] = new Button(((CharacterCell)(Game.map[i][j])).getCharacter().getName());
						Character a = ((CharacterCell)(Game.map[i][j])).getCharacter();
						String type;
						mapButtons[i][j].setOnMouseClicked(event -> {
							if(event.getClickCount() == 1 && chosenHero != null) 
								chosenHero.setTarget(a);
						});
						
						if(((CharacterCell)(Game.map[i][j])).getCharacter() instanceof Hero) {
							Hero aHero = (Hero)(a);
							if(aHero instanceof Fighter)
								type = "Fighter";
							else if(aHero instanceof Explorer)
								type = "Explorer";
							else
								type = "Medic";
							mapButtons[i][j] = new Button("Name: " + aHero.getName() + "\n" +"Type: "+ type +"\n" +
						    "HP: " + aHero.getCurrentHp() + "\n" + "Attack damage: " + aHero.getAttackDmg() +"\n"+
									"Max actions:" + aHero.getMaxActions());
							
							mapButtons[i][j].setOnMouseClicked(event -> {
								if(event.getClickCount() == 1 && chosenHero != null) 
									chosenHero.setTarget(a);
								
								else if(event.getClickCount() == 2){
								    chosenHero = aHero;
								    Popups.popUps("Stats", "Name: " + chosenHero.getName() +"\n"+ "Type: " + type + "\n" +
								    "HP: "+ chosenHero.getCurrentHp() +"\n"+ "Attack damage: " + chosenHero.getAttackDmg() +"\n"+
								    "Remaining action points: " + chosenHero.getActionsAvailable()  +"\n"+
								    "Supplies with this hero: "+ chosenHero.getSupplyInventory().size() +"\n"+
								    "Vaccines with this hero: "+ chosenHero.getVaccineInventory().size());
								}
							});
						}
					 }
					
					else if(Game.map[i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(Game.map[i][j])).getCollectible() instanceof Vaccine)
							mapButtons[i][j] = new Button("Vaccine");
						else
							mapButtons[i][j] = new Button("Supply ");
					}
					
					else
						mapButtons[i][j] = new Button("       ");
				
				GridPane.setConstraints(mapButtons[i][j], i, 14-j);
				mapGrid.getChildren().add(mapButtons[i][j]);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("THE LAST OF US");
		
		Game.loadHeroes("Heroes.csv");
		
		Label youWin = new Label("You Win!");
		Button exitWin = new Button("Exit the game");
		exitWin.setOnAction(e -> window.close());
		VBox Winning = new VBox(20);
		Winning.setAlignment(Pos.CENTER);
		Winning.getChildren().addAll(youWin, exitWin);
		Win = new Scene(Winning, 900, 600);
		
		Label youLost = new Label("You Lost!");
		Button exitLost = new Button("Exit the game");
		exitLost.setOnAction(e -> window.close());
		VBox Losing = new VBox(20);
		Losing.setAlignment(Pos.CENTER);
		Losing.getChildren().addAll(youLost, exitLost);
		Lose = new Scene(Losing, 900, 600);
		
		
		
		Label mainMenuLabel = new Label("Welcom to The Last Of Us");
		Button play = new Button("Play");
		play.setOnAction(e -> window.setScene(chooseType));
		
		VBox startingLayout = new VBox(50);
		startingLayout.getChildren().addAll(mainMenuLabel, play);
		startingLayout.setAlignment(Pos.CENTER);
		
		Button chooseFighters = new Button("A Fighter");
		chooseFighters.setOnAction(e -> window.setScene(Fighters));
		
		Button chooseExplorers = new Button("An Explorer");
		chooseExplorers.setOnAction(e -> window.setScene(Explorers));
		
		Button chooseMedics = new Button("A Medic");
		chooseMedics.setOnAction(e -> window.setScene(Medics));
		
		Label chooseHeroType = new Label("Choose the type \nof your Starting hero");
		
		VBox chooseHeroLay = new VBox(35);
		chooseHeroLay.getChildren().addAll(chooseHeroType, chooseFighters, chooseExplorers, chooseMedics);
		chooseHeroLay.setAlignment(Pos.CENTER);
		chooseType = new Scene(chooseHeroLay, 900, 600);
		
		
		
		
		////////////    Choosing  Fighters      ////////////
		
		Button retChooseTypeFigh = new Button("Switch to another type of hero");
		retChooseTypeFigh.setOnAction(e -> window.setScene(chooseType));
		
		StackPane retChooseTypeLayFigh = new StackPane();
		retChooseTypeLayFigh.getChildren().add(retChooseTypeFigh);
		retChooseTypeLayFigh.setAlignment(Pos.CENTER);
		
		Button chooseFighter1 = new Button("Choose Joel");
		chooseFighter1.setOnAction(e -> { 
			whichStarter = 0;
			window.setScene(gameMap);
			//Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label fighter1Stats = new Label("Name: Joel Miller" +"\n"+ "Type: Fighter" + "\n" +
		"HP: 140" +"\n"+ "Max actions: 5" +"\n"+ "Attack damage: 30");
		
		VBox fighter1Lay = new VBox(35);
		fighter1Lay.getChildren().addAll(fighter1Stats, chooseFighter1);
		fighter1Lay.setAlignment(Pos.CENTER);
		
		Button chooseFighter2 = new Button("Choose David");
		chooseFighter2.setOnAction(e -> { 
			whichStarter = 6;
			window.setScene(gameMap);
			//Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label fighter2Stats = new Label("Name: David" +"\n"+ "Type: Fighter" + "\n" +
				"HP: 150" +"\n"+ "Max actions: 4" +"\n"+ "Attack damage: 35");
		
		VBox fighter2Lay = new VBox(35);
		fighter2Lay.getChildren().addAll(fighter2Stats, chooseFighter2);
		fighter2Lay.setAlignment(Pos.CENTER);
		
		BorderPane fightersPage = new BorderPane();
		fightersPage.setRight(fighter2Lay);
		fightersPage.setLeft(fighter1Lay);
		fightersPage.setBottom(retChooseTypeLayFigh);
		Fighters = new Scene(fightersPage, 900, 600);
		
		////////////    Choose Explorers      ///////////
		
		Button retChooseTypeExp = new Button("Switch to another type of hero");
		retChooseTypeExp.setOnAction(e -> window.setScene(chooseType));
		
		StackPane retChooseTypeLayExp = new StackPane();
		retChooseTypeLayExp.getChildren().add(retChooseTypeExp);
		retChooseTypeLayExp.setAlignment(Pos.CENTER);
		
		Button chooseTess = new Button("Choose Tess");
		chooseTess.setOnAction(e -> {
			whichStarter = 2;
		    window.setScene(gameMap);
		    //Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label tessStats = new Label("Name: Tess" +"\n"+ "Type: Explorer" +"\n"+
		"HP: 80" +"\n"+ "Max actions 6" +"\n"+ "Attack damage: 20");
		
		VBox tessLay = new VBox(35);
		tessLay.getChildren().addAll(tessStats, chooseTess);
		tessLay.setAlignment(Pos.CENTER);
		
		Button chooseRiley = new Button("Choose Riley");
		chooseRiley.setOnAction(e -> {
			whichStarter = 3;
			window.setScene(gameMap);
			//Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label rileyStats = new Label("Name: Riley Abel" + "\n"+ "Type: Explorer" +"\n"+
		"HP: 90" +"\n"+ "Max actions: 5" +"\n"+ "Attack damage: 25");
		
		VBox rileyLay = new VBox(35);
		rileyLay.getChildren().addAll(rileyStats, chooseRiley);
		rileyLay.setAlignment(Pos.CENTER);
		
		Button chooseTommy = new Button("Choose Tommy");
		chooseTommy.setOnAction(e -> {
			whichStarter = 4;
			window.setScene(gameMap);
			//Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label tommyStats = new Label("Name: Tommy Miller" + "\n"+ "Type: Explorer" +"\n"+
				"HP: 95" +"\n"+ "Max actions: 5" +"\n"+ "Attack damage: 25");
		
		VBox tommyLay = new VBox(35);
		tommyLay.getChildren().addAll(tommyStats, chooseTommy);
		tommyLay.setAlignment(Pos.CENTER);
		
		BorderPane explorersPage = new BorderPane();
		explorersPage.setLeft(tessLay);
		explorersPage.setCenter(rileyLay);
		explorersPage.setRight(tommyLay);
		explorersPage.setBottom(retChooseTypeLayExp);
		Explorers = new Scene(explorersPage, 900, 600);
		
		////////////    Choosing Medics      ////////////
		
		Button retChooseTypeMid = new Button("Switch to another type of hero");
		retChooseTypeMid.setOnAction(e -> window.setScene(chooseType));
		
		StackPane retChooseTypeLayMid = new StackPane();
		retChooseTypeLayMid.getChildren().add(retChooseTypeMid);
		retChooseTypeLayMid.setAlignment(Pos.CENTER);
		
		Button chooseEllie = new Button("Choose Ellie");
		chooseEllie.setOnAction(e -> {
			whichStarter = 1;
			window.setScene(gameMap);
			//Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label ellieStats = new Label("Name: Ellie Williams" + "\n"+ "Type: Medic" +"\n"+
				"HP: 110" +"\n"+ "Max actions: 6" +"\n"+ "Attack damage: 15");
		
		VBox ellieLay = new VBox(35);
		ellieLay.getChildren().addAll(ellieStats, chooseEllie);
		ellieLay.setAlignment(Pos.CENTER);
		
		Button chooseBill = new Button("Choose Bill");
		chooseBill.setOnAction(e -> {
			whichStarter = 5;
			window.setScene(gameMap);
			//Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label billStats = new Label("Name: Bill" + "\n"+ "Type: Medic" +"\n"+
				"HP: 100" +"\n"+ "Max actions: 7" +"\n"+ "Attack damage: 10");
		
		VBox billLay = new VBox(35);
		billLay.getChildren().addAll(billStats, chooseBill);
		billLay.setAlignment(Pos.CENTER);
		
		Button chooseHenry = new Button("Choose Henry");
		chooseHenry.setOnAction(e -> {
			whichStarter = 7;
			window.setScene(gameMap);
			//Game.startGame(Game.availableHeroes.get(whichStarter));
		});
		
		Label henryStats = new Label("Name: Henry Burell" + "\n"+ "Type: Medic" +"\n"+
				"HP: 105" +"\n"+ "Max actions: 6" +"\n"+ "Attack damage: 15");
		
		VBox henryLay = new VBox(35);
		henryLay.getChildren().addAll(henryStats, chooseHenry);
		henryLay.setAlignment(Pos.CENTER);
		
		BorderPane medicsPage = new BorderPane();
		medicsPage.setLeft(ellieLay);
		medicsPage.setCenter(billLay);
		medicsPage.setRight(henryLay);
		medicsPage.setBottom(retChooseTypeLayMid);
		Medics = new Scene(medicsPage, 900, 600);
		
		
		///////////////    The game's map    ////////////////
		
		
		
		mapGrid.setPadding(new Insets(10, 10, 10, 10));
		mapGrid.setVgap(8);
		mapGrid.setHgap(8);
		
		Game.startGame(Game.availableHeroes.get(whichStarter));
		
		setIfVisible();
				
		
		
		Button attackButton = new Button("Attack");
		attackButton.setOnAction(e -> {
			try {
				chosenHero.attack();
				if(chosenHero.getTarget().getCurrentHp() >= 0) {
					mapGrid.getChildren().remove(mapButtons[chosenHero.getTarget().getLocation().x][chosenHero.getTarget().getLocation().y]);
					mapButtons[chosenHero.getTarget().getLocation().x][chosenHero.getTarget().getLocation().y] = new Button("       ");
					mapGrid.getChildren().add(mapButtons[chosenHero.getTarget().getLocation().x][chosenHero.getTarget().getLocation().y]);
				}
				if(Game.checkGameOver())
					window.setScene(Lose);
				if(Game.checkWin())
					window.setScene(Win);
			}
			catch(NullPointerException e1) {
				Popups.popUps("Wrong action", "You should choose the hero \n    you want to control first");
			}
			catch(NotEnoughActionsException e1) {
				Popups.popUps("Wrong action", "You need at least 1 action point \nto be able to attack.");
			}
			catch(InvalidTargetException e1) {
				if(chosenHero.getTarget() == null)
					Popups.popUps("Wrong action", "You should select a target to attack first.");
				else if(!(chosenHero.getTarget() instanceof Zombie))
					Popups.popUps("Wrong action", "You can only attack zombies.");
				else
					Popups.popUps("Wrong action", "You are only able to attack adjacent targets.");
			}
		});
		
		GridPane.setConstraints(attackButton, 20, 18);
		mapGrid.getChildren().add(attackButton);
		
		Button special = new Button("Use Special");
		special.setOnAction(e -> {
			try {
			    chosenHero.useSpecial();
			    setIfVisible();
			    if(chosenHero instanceof Explorer)
			    	setAllVisible();
			}
			catch(NullPointerException e1) {
				Popups.popUps("Wrong action", "You should choose the hero \nyou want to control first");
			}
			catch(NoAvailableResourcesException e1) {
				Popups.popUps("Wrong action", "You need to have at least 1 supply in \nyour inventory to use your special abililty.");
			}
			catch(InvalidTargetException e1) {
				if(chosenHero.getTarget() instanceof Zombie)
					Popups.popUps("Wrong action", "You can only cure fellow heroes.");
				else 
					Popups.popUps("Wrong action", "You are only able to heal adjacent targets.");
			}
		});
		
		GridPane.setConstraints(special, 30, 18);
		mapGrid.getChildren().add(special);
		
		Button cureZombie = new Button("Cure a Zombie");
		cureZombie.setOnAction(e -> {
			try {
				chosenHero.cure();
				mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x][chosenHero.getLocation().y]);
				setIfVisible();
				if(Game.checkGameOver())
					window.setScene(Lose);
				if(Game.checkWin())
					window.setScene(Win);
			}
			catch(NullPointerException e1) {
				Popups.popUps("Wrong action", "You should choose the hero \nyou want to control first");
			}
			catch(NoAvailableResourcesException e1) {
				Popups.popUps("Wrong action", "You need to have at least 1 vaccine \nin your inventory to be able to cure zombies.");
			}
			catch(NotEnoughActionsException e1) {
				Popups.popUps("Wrong action", "You need at least 1 action point \nin order to cure a zombie.");
			}
			catch(InvalidTargetException e1) {
				if(chosenHero.getTarget() == null)
					Popups.popUps("Wrong action", "You need to pick a target to cure first.");
				else if(!(chosenHero.checkDistance()))
					Popups.popUps("Wrong action", "You are only able to cure adjacent targets.");
				else
					Popups.popUps("Wrong action", "You can only cure zombies.");
			}
		});
		
		GridPane.setConstraints(cureZombie, 25, 18);
		mapGrid.getChildren().add(cureZombie);
		
		Button endTurnButton = new Button("End turn");
		endTurnButton.setOnAction(e->{
			try {
				Game.endTurn();
				setAllInvisible();
				setAllInvisible();
				setIfVisible();
				if(Game.checkGameOver())
					window.setScene(Lose);
				if(Game.checkWin())
					window.setScene(Win);
			}
			catch(InvalidTargetException e1) {
				
			}
			catch(NotEnoughActionsException e1) {
				
			}
		});
		
		GridPane.setConstraints(endTurnButton, 35, 18);
		mapGrid.getChildren().add(endTurnButton);
		
		Button moveDown = new Button("Down");
		moveDown.setOnMouseClicked(e ->{
			try {
				boolean onTrap = false;
				if (Game.map[chosenHero.getLocation().x][chosenHero.getLocation().y - 1] != null)
				    mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x][chosenHero.getLocation().y]);
				if(Game.map[chosenHero.getLocation().x][chosenHero.getLocation().y - 1]instanceof TrapCell)
					onTrap = true;
				if(Game.map[chosenHero.getLocation().x][chosenHero.getLocation().y - 1]instanceof CollectibleCell)
					mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x][chosenHero.getLocation().y - 1]);
				chosenHero.move(Direction.LEFT);
				if(onTrap) {
					Popups.popUps("Oops...", "You have walked on a trap!");
					onTrap = false;
				}
				setIfVisible();
				if(Game.checkGameOver())
					window.setScene(Lose);
				if(Game.checkWin())
					window.setScene(Win);
				
			}
			catch(NullPointerException e1) {
				Popups.popUps("Wrong action", "You should choose the hero \nyou want to control first");
			}
			catch(NotEnoughActionsException e1) {
				Popups.popUps("Wrong action", "You need at least 1 action point in order to move.");
				setIfVisible();
			}
			catch(MovementException e1) {
				if(chosenHero.getLocation().y - 1 < 0)
					Popups.popUps("Wrong action", "You cannot move outside the borders of the map.");
				else {
					Popups.popUps("Wrong action", "You cannot move to an occuppied cell.");
					setIfVisible();
				}
			}
		});
		
		Button moveRight = new Button("Right");
		moveRight.setOnMouseClicked(e -> {
			try {
				boolean onTrap = false;
				if (Game.map[chosenHero.getLocation().x + 1][chosenHero.getLocation().y] != null)
				    mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x][chosenHero.getLocation().y]);
				if(Game.map[chosenHero.getLocation().x + 1][chosenHero.getLocation().y]instanceof TrapCell)
					onTrap = true;
				if(Game.map[chosenHero.getLocation().x + 1][chosenHero.getLocation().y]instanceof CollectibleCell)
					mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x + 1][chosenHero.getLocation().y]);
				chosenHero.move(Direction.UP);
				if(onTrap) {
					Popups.popUps("Oops...", "You have walked on a trap!");
					onTrap = false;
				}
				setIfVisible();
				if(Game.checkGameOver())
					window.setScene(Lose);
				if(Game.checkWin())
					window.setScene(Win);
				
			}
			catch(NullPointerException e1) {
				Popups.popUps("Wrong action", "You should choose the hero \nyou want to control first");
			}
			catch(NotEnoughActionsException e1) {
				Popups.popUps("Wrong action", "You need at least 1 action point in order to move.");
				setIfVisible();
			}
			catch(MovementException e1) {
				if(chosenHero.getLocation().x + 1 > Game.map.length - 1)
					Popups.popUps("Wrong action", "You cannot move outside the borders of the map.");
				else {
					Popups.popUps("Wrong action", "You cannot move to an occuppied cell.");
					setIfVisible();
				}
			}
		});
		
		
		Button moveLeft = new Button("Left");
		moveLeft.setOnMouseClicked(e ->{
			try {
				boolean onTrap = false;
				if (Game.map[chosenHero.getLocation().x - 1][chosenHero.getLocation().y] != null)
				    mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x][chosenHero.getLocation().y]);
				if(Game.map[chosenHero.getLocation().x - 1][chosenHero.getLocation().y]instanceof TrapCell)
					onTrap = true;
				if(Game.map[chosenHero.getLocation().x - 1][chosenHero.getLocation().y]instanceof CollectibleCell)
					mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x - 1][chosenHero.getLocation().y]);
				chosenHero.move(Direction.DOWN);
				if(onTrap) {
					Popups.popUps("Oops...", "You have walked on a trap!");
					onTrap = false;
				}
				setIfVisible();
				if(Game.checkGameOver())
					window.setScene(Lose);
				if(Game.checkWin())
					window.setScene(Win);
				
			}
			catch(NullPointerException e1) {
				Popups.popUps("Wrong action", "You should choose the hero \nyou want to control first");
			}
			catch(NotEnoughActionsException e1) {
				Popups.popUps("Wrong action", "You need at least 1 action point in order to move.");
				setIfVisible();
			}
			catch(MovementException e1) {
				if(chosenHero.getLocation().x - 1 < 0)
					Popups.popUps("Wrong action", "You cannot move outside the borders of the map.");
				else {
					Popups.popUps("Wrong action", "You cannot move to an occuppied cell.");
					setIfVisible();
				}
			}
		});
		
		
		Button moveUp = new Button("Up");
		moveUp.setOnMouseClicked(e ->{
			try {
				boolean onTrap = false;
				if (Game.map[chosenHero.getLocation().x][chosenHero.getLocation().y + 1] != null)
				    mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x][chosenHero.getLocation().y]);
				if(Game.map[chosenHero.getLocation().x][chosenHero.getLocation().y + 1]instanceof TrapCell)
					onTrap = true;
				if(Game.map[chosenHero.getLocation().x][chosenHero.getLocation().y + 1]instanceof CollectibleCell)
					mapGrid.getChildren().remove(mapButtons[chosenHero.getLocation().x][chosenHero.getLocation().y + 1]);
				chosenHero.move(Direction.RIGHT);
				if(onTrap) {
					Popups.popUps("Oops...", "You have walked on a trap!");
					onTrap = false;
				}
				setIfVisible();
				if(Game.checkGameOver())
					window.setScene(Lose);
				if(Game.checkWin())
					window.setScene(Win);
				
			}
			catch(NullPointerException e1) {
				Popups.popUps("Wrong action", "You should choose the hero \nyou want to control first");
			}
			catch(NotEnoughActionsException e1) {
				Popups.popUps("Wrong action", "You need at least 1 action point in order to move.");
				setIfVisible();
			}
			catch(MovementException e1) {
				if(chosenHero.getLocation().y + 1 > Game.map.length - 1)
					Popups.popUps("Wrong action", "You cannot move outside the borders of the map.");
				else {
					Popups.popUps("Wrong action", "You cannot move to an occuppied cell.");
					setIfVisible();
				}
			}
		});
		
		
		
		GridPane.setConstraints(moveLeft, 20, 19);
		GridPane.setConstraints(moveUp, 25, 19);
		GridPane.setConstraints(moveDown, 30, 19);
		GridPane.setConstraints(moveRight, 35, 19);
		mapGrid.getChildren().addAll(moveLeft, moveUp, moveDown, moveRight);
		
		gameMap = new Scene(mapGrid, 900, 600);
		
		
		mainMenu = new Scene(startingLayout, 900, 600);
		window.setScene(mainMenu);
		window.show();
		
	}

}
