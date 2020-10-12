package janken;

import java.util.Random;

public class Play {

	private Player player;
	private int computer_score;

	public void start() {
		player = new Player();
		star_line("Janken");
		do {
			int count = User.getInt("How many time do u want to play");
			for (int i = 1; i <= count; i++) {
				System.out.println();
				play(showMessage());
			}
			System.out.println();
			System.out.println("Total Score :Player Score ("+ player.getScore()+") - Computer Score (" + computer_score + ")");
		} while ("yes".equalsIgnoreCase(User.getString("Do u want to try again yes/no:")));
		
		star_line("Bye Bye");
	}

	private void star_line(String string) {
		String line = "";
		for (int i = 0; i < string.length() + 1; i++) {
			line += "*";
		}
		System.out.println(string + "\n" + line);
	}

	private void play(String str) {

		switch (str) {
		case "PAPER":
			player.setChoice(Choice.PAPER);
			switch (randomChoice()) {
			case PAPER:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.PAPER);
				System.out.println("withdraw");
				
				break;

			case ROCK:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.ROCK);
				System.out.println("Player Win");
				player.setScore(+1);
				break;

			case SCISSOR:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.SCISSOR);
				System.out.println("Computer Win");
				computer_score++;
				break;
			}
			break;

		case "ROCK":
			player.setChoice(Choice.ROCK);
			switch (randomChoice()) {
			case PAPER:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.PAPER);
				System.out.println("Computer Win");
				computer_score++;
				break;

			case ROCK:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.ROCK);
				System.out.println("withdraw");
				break;

			case SCISSOR:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.SCISSOR);
				System.out.println("Player Win");
				player.setScore(+1);
				break;
			}
			break;

		case "SCISSOR":
			player.setChoice(Choice.SCISSOR);
			switch (randomChoice()) {
			case PAPER:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.PAPER);
				System.out.println("Player Win");
				player.setScore(+1);
				break;

			case ROCK:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.ROCK);
				System.out.println("Computer Win");
				computer_score++;
				break;

			case SCISSOR:
				System.out.println("Player Choice : " + player.getChoice());
				System.out.println("Computer Choice : " + Choice.SCISSOR);
				System.out.println("withdraw");
				break;
			}
			break;

		default:
			System.out.println("Sorry");
			System.out.println("Enter only these three choices ");
			for (Choice ch : Choice.values()) {
				System.out.print(" " + ch + " | ");
			}
			System.out.println("");
		}
	}

	private String showMessage() {
		for (Choice ch : Choice.values()) {
			System.out.println(ch);
		}
		return User.getString("Enter your choice : ").toUpperCase();
	}

	private Choice randomChoice() {
		int pick = new Random().nextInt(Choice.values().length);
		return Choice.values()[pick];
	}
}
