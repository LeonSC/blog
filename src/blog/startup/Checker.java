package blog.startup;

import blog.model.Setting;

public class Checker {

	private static Checker checker;
	private Checker() {}
	public static Checker getChecker() {
		return Checker.checker == null ? new Checker() : Checker.checker;
	}
	
	public static int qiniuChecker=0;//0不能使用,1能使用
	public int getQiniuChecker() {
		return qiniuChecker;
	}
	public static Setting setting=null;
}
