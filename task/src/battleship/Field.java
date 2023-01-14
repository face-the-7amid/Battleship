package battleship;

import java.util.*;

public class Field {
    private String[][] field;
    private Ship[] bateaux=new Ship[5];
    public Field() {
        this.field = new String[11][11];
        this.bateaux[0]=new Ship("Aircraft Carrier",5);
        this.bateaux[1]=new Ship("Battleship",4);
        this.bateaux[2]=new Ship("Submarine",3);
        this.bateaux[3]=new Ship("Cruiser",3);
        this.bateaux[4]=new Ship("Destroyer",2);
    }

    public String[][] getField() {
        return field;
    }
    public void setField(String[][] field) {
        this.field = field;
    }

    public void initialiser(){
        field[0][0]=" ";
        int ascii=65;
        for(int i=1;i<11;i++) {
            field[0][i]=String.valueOf(i);
            field[i][0]=Character.toString((char)ascii);
            ascii++;
            for(int j=1;j<11;j++) {
                field[i][j]="~";
            }
        }
    }

    public void afficher(){
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
    }

    public boolean verifDistance(int r1,int col1,int r2,int col2,int size){
        if(r1-r2==0){
            if(Math.abs(col1-col2)+1==size){
                return true;
            }
        }
        else if(Math.abs(r1-r2)+1==size){
            if(col1-col2==0){
                return true;
            }
        }
        return false;
    }

    public boolean verifierEntourage(int row,int col){
        if(row==1) {
            if(col==1) {
                if(field[row][col]=="~"&& field[row+1][col]=="~" &&  field[row][col+1]=="~"){
                    return true;
                }
            }
            if(col==10) {
                if(field[row][col]=="~"&& field[row+1][col]=="~" &&  field[row][col-1]=="~"){
                    return true;
                }
            }
            else {
                if(field[row][col]=="~"&& field[row+1][col]=="~" && field[row][col-1]=="~" && field[row][col+1]=="~"){
                    return true;
                }
            }
        }
        else if(row==10) {
            if(col==1) {
                if(field[row][col]=="~"&& field[row-1][col]=="~" &&  field[row][col+1]=="~"){
                    return true;
                }
            }
            if(col==10) {
                if(field[row][col]=="~"&& field[row-1][col]=="~" &&  field[row][col-1]=="~"){
                    return true;
                }
            }
            else {
                if(field[row][col]=="~"&& field[row-1][col]=="~" && field[row-1][col]=="~" && field[row][col+1]=="~"){
                    return true;
                }
            }
        }
        else if(col==1){
            if(field[row][col]=="~"&& field[row-1][col]=="~" && field[row+1][col]=="~" && field[row][col+1]=="~"){
                return true;
            }
        }
        else if(col==10){
            if(field[row][col]=="~"&& field[row-1][col]=="~" && field[row+1][col]=="~" && field[row][col-1]=="~"){
                return true;
            }
        }
        else{
            if(field[row][col]=="~"&& field[row-1][col]=="~" && field[row+1][col]=="~" && field[row][col-1]=="~"&& field[row][col+1]=="~"){
                return true;
            }
        }
        return false;
    }

    public boolean verifierValidite(int r1,int col1,int r2,int col2){
        if(r1!=r2){
            for(int i=Math.min(r1,r2);i<=Math.max(r1,r2);i++){
                if(!verifierEntourage(i,col1)){
                    return false;
                }
            }
        }
        else{
            for(int i=Math.min(col1,col2);i<=Math.max(col1,col2);i++){
                if(!verifierEntourage(r1,i)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean testerCord(String cord1,String cord2,int i){
        int r1 = (int) cord1.charAt(0)-64;
        int r2 = (int) cord2.charAt(0)-64;
        int col1=Integer. parseInt(cord1.substring(1));
        int col2=Integer. parseInt(cord2.substring(1));
        if(verifDistance(r1,col1,r2,col2,bateaux[i].getSize())){
            if(verifierValidite(r1,col1,r2,col2)){
                return true;
            }
        }
        return false;
    }

    public void modifier(String cord1,String cord2){
        int r1 = (int) cord1.charAt(0)-64;
        int r2 = (int) cord2.charAt(0)-64;
        int col1=Integer. parseInt(cord1.substring(1));
        int col2=Integer. parseInt(cord2.substring(1));
        if(r1!=r2){
            for(int i=Math.min(r1,r2);i<=Math.max(r1,r2);i++){
                field[i][col1]="O";
            }
        }
        else{
            for(int i=Math.min(col1,col2);i<=Math.max(col1,col2);i++){
                field[r1][i]="O";
            }
        }
    }

    public Ship[] getBateaux() {
        return bateaux;
    }

    public boolean testerSyntaxe(String cord){
        int r = (int) cord.charAt(0);
        int col=Integer. parseInt(cord.substring(1));
        return !(r<65 || r>74 || col>10 || col<1);
    }
    public void attack(Field map,List<String> set,Set<String> backUp,String cord){
        int r = (int) cord.charAt(0)-64;
        int col=Integer. parseInt(cord.substring(1));
        int indice=set.indexOf(cord);
        if(set.contains(cord)){
            field[r][col]="X";
            if(set.size()==11){
                System.out.println("\nYou sank the last ship. You won. Congratulations!\n");
            }
            else{
                if(set.get(indice-1)=="M" && set.get(indice+1)=="M"){
                    System.out.println("You sank a ship!\n");
                }
                else{
                    System.out.println("You hit a ship!\n");
                }
            }
            map.getField()[r][col]="X";
            set.remove(indice);
            //map.afficher();
        }
        else{
            if(backUp.contains(cord)){
                System.out.println("You hit a ship!\n");

            }
            else{
                field[r][col]="M";
                System.out.println("\nYou missed!\n");
                map.getField()[r][col]="M";
                //map.afficher();
            }
        }
    }

    public void ajouterCord(List<String> sett, Set<String> backup, String cord1, String cord2) {
        int r1 = (int) cord1.charAt(0)-64;
        int r2 = (int) cord2.charAt(0)-64;
        int col1=Integer. parseInt(cord1.substring(1));
        int col2=Integer. parseInt(cord2.substring(1));
        String testo;
        sett.add("M");
        if(r1!=r2){
            for(int i=Math.min(r1,r2);i<=Math.max(r1,r2);i++){
                testo=Character.toString((char)i+64)+col1;
                sett.add(testo);
                backup.add(testo);
            }
        }
        else{
            for(int i=Math.min(col1,col2);i<=Math.max(col1,col2);i++){
                testo=Character.toString((char)r1+64)+i;
                sett.add(testo);
                backup.add(testo);
            }
        }
        sett.add("M");
    }
    
    public void putShips(int number,List<String> emplacement,Set<String> backupEmplacement){
        System.out.printf("Player %d, place your ships on the game field\n",number);
        initialiser();
        afficher();
        int k=0;
        for(Ship s: getBateaux()){
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n",s.getName(),s.getSize());
            boolean isGood=false;
            do {
                Scanner scanner=new Scanner(System.in);
                String cord1=scanner.next();
                String cord2=scanner.next();
                isGood = testerCord(cord1, cord2,k);
                if(!isGood) System.out.println("\nError,choose valid coordinates (coordinates that aren't near any other ship) :Try Again !!!\n");
                else{
                    modifier(cord1,cord2);
                    ajouterCord(emplacement,backupEmplacement,cord1,cord2);
                }
            }while(!isGood);
            afficher();
            k++;
        }
    }

    public void playLoop(Field fogMap,Field ennemyMap,int numba,List<String> emplacement,Set<String> backupEmplacement){
        fogMap.afficher();
        System.out.println("---------------------\n");
        afficher();
        System.out.printf("\nPlayer %d, it's your turn:\n",numba);
        boolean test=false;
        do {
            Scanner scanner = new Scanner(System.in);
            String cord=scanner.next();
            test=testerSyntaxe(cord);
            if(!test) System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            else fogMap.attack(ennemyMap,emplacement,backupEmplacement,cord);
        }while(!test);
    }

    public  void passerTour(){
        System.out.println("Press Enter and pass the move to another player\n");
        Scanner scanner=new Scanner(System.in);
        String back= scanner.nextLine();
        while(back.length()!=0){
            System.out.println("Press Enter and pass the move to another player\n");
            scanner=new Scanner(System.in);
            back= scanner.nextLine();
        }
    }

    public static void reset(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

