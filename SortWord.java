

public class SortWord {
    public static  int countWord(String text){
        int sum = 0;
        for (int i = 0; i < text.length(); i++) {
            sum += text.charAt(i);
        }
        return sum;
    }
    public  static void SortByASC_Count(String[] word){
        for (int i = 0; i < word.length; i++) {
            int room = i;
            for (int j = i+1; j < word.length; j++) {
                if(countWord(word[room])>countWord(word[j])){
                    room = j;
                }
            }
            String temp = word[i];
            word[i] = word[room];
            word[room] = temp;
        }
    }
    public  static void SortByDESC_Count(String[] word){
        for (int i = 0; i < word.length; i++) {
            int room = i;
            for (int j = i+1; j < word.length; j++) {
                if(countWord(word[room])<countWord(word[j])){
                    room = j;
                }
            }
            String temp = word[i];
            word[i] = word[room];
            word[room] = temp;
        }
    }
    public  static void SortByASC(String[] word){
        for (int i = 0; i < word.length; i++) {
            int room = i;
            for (int j = i+1; j < word.length; j++) {
                if(word[room].compareTo(word[j])>0){
                    room = j;
                }
            }
            String temp = word[i];
            word[i] = word[room];
            word[room] = temp;
        }
    }
    public  static void SortByDESC(String[] word){
        for (int i = 0; i < word.length; i++) {
            int room = i;
            for (int j = i+1; j < word.length; j++) {
                if(word[room].compareTo(word[j])<0){
                    room = j;
                }
            }
            String temp = word[i];
            word[i] = word[room];
            word[room] = temp;
        }
    }
    public static void main(String[] args) {
        String[]  Text = {"AA","B","C","D"};
        SortByDESC_Count(Text);
        for (int i = 0; i < Text.length; i++) {
            System.out.print(Text[i]+" ");
        }
        System.out.println();
        SortByASC_Count(Text);
        for (int i = 0; i < Text.length; i++) {
            System.out.print(Text[i]+" ");
        }
        System.out.println();
        SortByDESC(Text);
        for (int i = 0; i < Text.length; i++) {
            System.out.print(Text[i]+" ");
        }
        System.out.println();
        SortByASC(Text);
        for (int i = 0; i < Text.length; i++) {
            System.out.print(Text[i]+" ");
        }
        System.out.println();
    }
}
