public class CodingBat {

    public static void main(String[] args) {

        String str = "xy*yzz";
        int ind = str.indexOf("*");
        if (ind < 1) System.out.println(99);;
        String letter = str.substring(ind-1, ind);
        System.out.println(letter + " " + str.substring(ind+1, ind+2));
        System.out.println(letter == str.substring(ind+1, ind+2));

    }

    public String getSandwich(String str) {
        if (str.length() < 5) return "";
        int last=0, x2=0, x1 = str.indexOf("bread")+5;
        String change = str.substring(x1, str.length());
        while(change.contains("bread")){
            x2 = change.indexOf("bread");
            if (x2 == 0) last+=5;
            last+=x2;
            if (x2+5 < change.length()) change = change.substring(x2+5, change.length());
            else change = "";
        }
        if (x2 == 0) last-=5;

        if (last < 0) return "";
        return str.substring(x1, last+x1);
    }


    public boolean prefixAgain(String str, int n) {
        String prefix = str.substring(0,n);
        String pos = str.substring(n);
        return pos.contains(prefix);
    }


    public String repeatSeparator(String word, String sep, int count) {
        String str = "";
        for(int i = 0; i < count; i++){
            if (str.length()>0) str+=sep;
            str+=word;
        }
        return str;
    }


    public String repeatFront(String str, int n) {
        String strr ="", strx = str.substring(0, n);
        int c = 1;
        for (int l = 0; l < n; l++){
            strr+=strx;
            strx = strx.substring(0, n-c);
            c++;
        }
        return strr;
    }



    public String repeatEnd(String str, int n) {
        String strr ="", strx = str.substring(str.length()-n);
        for (int l = 0; l < n; l++){
            strr+=strx;
        }
        return strr;
    }


    public String mixString(String a, String b) {
        String strr = "";
        for (int l = 0; l < a.length() || l < b.length(); l++){
            if (l < a.length()) strr+=a.substring(l,l+1);
            if (l < b.length()) strr+=b.substring(l,l+1);
        }
        return strr;
    }


    public boolean xyBalance(String str) {
        boolean balance = true;

        for (int l = 0; l < str.length(); l++){
            if (str.substring(l,l+1).equals("x")) balance = false;
            if (str.substring(l,l+1).equals("y")) balance = true;
        }
        return balance;
    }


    public boolean bobThere(String str) {
        for (int l = 0; l < str.length(); l++){
            if (l+2<str.length() && str.substring(l, l+1).equals("b") && str.substring(l+2, l+3).equals("b")) return true;
        }
        return false;
    }


    public boolean xyzThere(String str) {
        int count = 0;
        for (int l = 0; l < str.length(); l++){
            if (l+2 < str.length() && str.substring(l,l+3).equalsIgnoreCase("xyz")) count++;
            if (l+3 < str.length() && str.substring(l,l+4).equalsIgnoreCase(".xyz")) count--;
        }
        return count>0;
    }


    public boolean endOther(String a, String b) {
        if (b.toLowerCase().endsWith(a.toLowerCase()) || a.toLowerCase().endsWith(b.toLowerCase())) return true;
        return false;
    }


    public int countCode(String str) {
        int count = 0;
        for (int l = 0; l < str.length(); l++){
            if (l+3<str.length() && str.substring(l, l+2).equals("co") && str.substring(l+3, l+4).equals("e")) count++;
        }
        return count;
    }


    public boolean catDog(String str) {
        int countcat = 0, countdog = 0;
        for (int l = 0; l < str.length(); l++){
            if (l+2 < str.length() && str.substring(l,l+3).equalsIgnoreCase("cat")) countcat++;
            if (l+2 < str.length() && str.substring(l,l+3).equalsIgnoreCase("dog")) countdog++;
        }
        return countcat==countdog;
    }


    public int countHi(String str) {
        int strr = 0;
        for (int l = 0; l < str.length(); l++){
            if (l+1 < str.length() && str.substring(l,l+2).equals("hi")) strr++;
        }
        return strr;
    }


    public String doubleChar(String str) {
        String strr = "";
        for (int l = 0; l < str.length(); l++){
            strr += str.substring(l,l+1)+str.substring(l,l+1);
        }
        return strr;
    }


    public static void out(String txt){
        System.out.println(txt);
    }

}
