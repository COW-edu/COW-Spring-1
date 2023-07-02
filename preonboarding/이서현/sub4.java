import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class sub4 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		Vector<Character> str = new Vector<Character>();
		int num=System.in.read();
		for(int i=0;num!=0x0d; i++) {
			str.add((char)num);
			num=System.in.read();
		}
		str=solution(str);
		for(int i=0; i<str.size(); i++) {
			System.out.print(str.get(i));
		}
	}

	private static Vector<Character> solution(Vector<Character> printstr) {
		// TODO Auto-generated method stub
		Vector<Character> newstr = new Vector<Character>();

		for(int i=0; i<printstr.size();i++) {
			int numchar=(int)printstr.get(i);
			int startsubtract=numchar-65;
			int endsubtract = 90-numchar;
			if(numchar>96 && numchar<123) {
				startsubtract=numchar-97;
				endsubtract=122-numchar;
				if(startsubtract>12) {
					numchar=97+endsubtract;
				}else {
					numchar=122-startsubtract;
				}
			}else if(numchar>64 && numchar<91){
				if(startsubtract>12) {
					numchar=65+endsubtract;
				}else {
					numchar=90-startsubtract;
				}
			}else {
				//empty
			}
			newstr.add((char)numchar);
		}
		
		return newstr;
	}

}
