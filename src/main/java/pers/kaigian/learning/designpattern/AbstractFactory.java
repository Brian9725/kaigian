package pers.kaigian.learning.designpattern;

/**
 * @Author BrianHu
 * @Create 2021-04-21 14:46
 **/
public class AbstractFactory {
	public static void main(String[] args) {
		IDataBaseUtil dataBaseUtil = new MySQLUtil();
		IConnection connection = dataBaseUtil.getConnection();
		ICommand command = dataBaseUtil.getCommand();
		connection.connect();
		command.command();
	}
}

interface IConnection {
	void connect();
}

interface ICommand {
	void command();
}

interface IDataBaseUtil {
	IConnection getConnection();

	ICommand getCommand();
}

class MySQLConnection implements IConnection {
	@Override
	public void connect() {
		System.out.println("mysql connect");
	}
}

class MySQLCommand implements ICommand {
	@Override
	public void command() {
		System.out.println("mysql command");
	}
}

class MySQLUtil implements IDataBaseUtil {
	@Override
	public IConnection getConnection() {
		return new MySQLConnection();
	}

	@Override
	public ICommand getCommand() {
		return new MySQLCommand();
	}
}
