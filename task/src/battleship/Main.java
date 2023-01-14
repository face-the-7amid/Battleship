package battleship;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //putting ships

        Set<String> backupEmplacement1=new TreeSet<>();
        List<String> emplacement1=new ArrayList<>();
        Set<String> backupEmplacement2=new TreeSet<>();
        List<String> emplacement2=new ArrayList<>();

        Field mapShips1=new Field();
        Field mapShips2=new Field();
        Field mapFog1=new Field();
        Field mapFog2=new Field();

        mapShips1.initialiser();
        mapShips2.initialiser();
        mapFog1.initialiser();
        mapFog2.initialiser();

        mapShips1.putShips(1,emplacement1,backupEmplacement1);
        mapShips1.passerTour();
        Field.reset();
        mapShips2.putShips(2,emplacement2,backupEmplacement2);


        do{
            mapShips2.passerTour();
            Field.reset();
            mapShips1.playLoop(mapFog2,mapShips2,1,emplacement2,backupEmplacement2);
            if(emplacement1.size()!=10){
                mapShips1.passerTour();
                Field.reset();
                mapShips2.playLoop(mapFog1,mapShips1,2,emplacement1,backupEmplacement1);
            }
        } while(emplacement1.size()!=10 && emplacement2.size()!=10);
        //old version
        /*int k=0;
        Set<String> backupEmplacement=new TreeSet<>();
        List<String> emplacement=new ArrayList<>();
        for(Ship s:map1.getBateaux()){
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n",s.getName(),s.getSize());
            boolean isGood=false;
            do {
                Scanner scanner=new Scanner(System.in);
                String cord1=scanner.next();
                String cord2=scanner.next();
                isGood = map1.testerCord(cord1, cord2,k);
                if(!isGood) System.out.println("\nError,choose valid coordinates (coordinates that aren't near any other ship) :Try Again !!!\n");
                else{
                    map1.modifier(cord1,cord2);
                    map1.ajouterCord(emplacement,backupEmplacement,cord1,cord2);
                }
            }while(!isGood);
            map1.afficher();
            k++;
        }
        // game starts
        System.out.println("The game starts!\n");
        Field map2=new Field();
        map2.initialiser();
        map2.afficher();
        System.out.println("Take a shot!\n");
        do{
            boolean test=false;
            do {
                Scanner scanner = new Scanner(System.in);
                String cord=scanner.next();
                test=map1.testerSyntaxe(cord);
                if(!test) System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                else map2.attack(map1,emplacement,backupEmplacement,cord);
            }while(!test);
        }while(emplacement.size()!=10);*/
    }
}
