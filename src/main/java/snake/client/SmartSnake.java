package snake.client;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SmartSnake {
    private Point stone;
    private Point apple;
    private Lee lee;
    private Point snake_head;
    private boolean gameOver;
    private ArrayList<Point> obstacles = new ArrayList<>();
    private List<Point> tailList = new ArrayList<>();
    private Point pseudoAppleUL;
    private Point pseudoAppleUR;
    private Point pseudoAppleDL;
    private Point pseudoAppleDR;
    private Point pseudoHead1;
    private Point pseudoHead2;
    private Point pseudoHead3;
    private Point pseudoHead4;
    private Point pseudoHead5;
    private Point pseudoHead6;
    private Point pseudoHead7;
    private Point pseudoHead8;
    private Point tail;

    public SmartSnake(Board board) {

        List<Point> walls = board.getWalls();
        gameOver = board.isGameOver();
        stone = board.getStones().get(0);
        apple = board.getApples().get(0);
        snake_head = board.getHead();
        List<Point> snake = board.getSnake();

        if (board.getTail() != null) {
            tail = board.getTail();
            //tailList1=board.getTailList(tail);

            /*Point tempHead=snake_head.copy();
            headNext=tempHead.copy();
            headNext.setX(snake_head.getX()+1);
            tempHead.setY(tempHead.getY()+1);
           for (int i = 0; i <snake.size()-1 ; i++) {
                if (snake.contains(headNext)){
                    tempHead.setX(headNext.getX()+1);
                    tailList.add(headNext);
                    System.out.println("Headnext"+headNext);
                    System.out.println("TempHead"+headNext);
                    headNext=tempHead;
                    System.out.println("Tail = "+tail);
                    System.out.println("TailLIst=" + tailList);
                    System.out.println();
                } else {
                    headNext.setX(snake_head.getX()+1);
                    headNext.setY(snake_head.getY()-1);
                    if (snake.contains(headNext)){
                        //tailList.add(headNext);
                        headNext=tempHead;
                    } else {
                        headNext.setX(snake_head.getX()-1);
                        headNext.setY(snake_head.getY()+1);
                        if (snake.contains(headNext)){
                          //  tailList.add(headNext);
                            headNext=tempHead;
                        } else {
                            headNext.setX(snake_head.getX()-1);
                            headNext.setY(snake_head.getY()-1);
                            if (snake.contains(headNext)){
                              //  tailList.add(headNext);
                                headNext=tempHead;
                            } else {
                                headNext.setX(snake_head.getX()+1);
                                if (snake.contains(headNext)){
                                  //  tailList.add(headNext);
                                    headNext=tempHead;
                                } else {
                                    headNext.setX(snake_head.getX()-1);

                                    if (snake.contains(headNext)){
                                     //   tailList.add(headNext);
                                        headNext=tempHead;
                                    } else {
                                        headNext.setY(snake_head.getY()+1);
                                        if (snake.contains(headNext)){
                                          //  tailList.add(headNext);
                                            headNext=tempHead;
                                        } else {
                                            headNext.setY(snake_head.getY()-1);
                                            if (snake.contains(headNext)){
                                              //  tailList.add(headNext);
                                                headNext=tempHead;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
        } else {
            tail = apple.copy();
        }
        tailList=snake;
        List<Point> tempTail= new ArrayList<>();
       // tempTail.add(snake_head);

        for (int i = 0, j=1; i <=tempTail.size()&&j<tailList.size() ; i++) {
            Point temp=tailList.get(j);
                if (tempTail.size()>0
                        &&tempTail.size()>i
                        &&(temp.getX() >= tempTail.get(i).getX()
                        &&temp.getY() == tempTail.get(i).getY())) {
                    continue;
                }
                else

                    {
                    tempTail.add(temp);
                   // System.out.println("Temptail= "+tempTail);
                    j++;
                }
            if (tempTail.size()>0
                    &&tempTail.size()>i
                    &&(temp.getX() == tempTail.get(i).getX()
                    &&temp.getY() > tempTail.get(i).getY())) {
                Point temp1=tempTail.get(i);
                tempTail.set(i,temp);
                tempTail.add(temp1);
            }
            if (tempTail.size()>0
                    &&tempTail.size()>i
                    &&(temp.getX() >tempTail.get(i).getX()
                    &&temp.getY() < tempTail.get(i).getY())) {
                Point temp1=tempTail.get(i);
                tempTail.set(i,temp);
                tempTail.add(temp1);
            }

            /*if (temp!=null &&
                    (temp.getX() >= tempTail.get(i-2).getX()
                            &&temp.getY() <= tempTail.get(i-2).getY())) {
                tempTail.add(temp);
            }
            else {
                tempTail.add(tailList.get(i));
            }*/

        }
        tempTail.remove(tail);
        tempTail.add(tail);
        tailList=tempTail;
        int dimX = walls.stream().mapToInt(Point::getX).max().orElse(0) + 1;
        int dimY = walls.stream().mapToInt(Point::getY).max().orElse(0) + 1;
        obstacles.addAll(walls);
        obstacles.add(stone);
        obstacles.addAll(snake);
        lee = new Lee(dimX, dimY);
    }

    Direction solve() {

        pseudoAppleUL = apple.copy();
        pseudoAppleUR = apple.copy();
        pseudoAppleDL = apple.copy();
        pseudoAppleDR = apple.copy();
        pseudoHead1 = apple.copy();
        pseudoHead2 = apple.copy();
        pseudoHead3 = apple.copy();
        pseudoHead4 = apple.copy();
        pseudoHead5 = apple.copy();
        pseudoHead6 = apple.copy();
        pseudoHead7 = apple.copy();
        pseudoHead8 = apple.copy();

        pseudoAppleUL.setX(3);
        pseudoAppleUL.setY(11);

        pseudoAppleUR.setX(11);
        pseudoAppleUR.setY(11);

        pseudoAppleDL.setX(3);
        pseudoAppleDL.setY(3);

        pseudoAppleDR.setX(11);
        pseudoAppleDR.setY(3);
        if (snake_head != null) {
            pseudoHead1.setX(snake_head.getX() + 1);

            pseudoHead2.setX(snake_head.getX() - 1);

            pseudoHead3.setY(snake_head.getY() + 1);

            pseudoHead4.setY(snake_head.getY() - 1);

            pseudoHead5.setX(snake_head.getX() + 1);
            pseudoHead5.setY(snake_head.getY() + 1);

            pseudoHead6.setX(snake_head.getX() + 1);
            pseudoHead6.setY(snake_head.getY() - 1);

            pseudoHead7.setX(snake_head.getX() - 1);
            pseudoHead7.setY(snake_head.getY() + 1);

            pseudoHead8.setX(snake_head.getX() - 1);
            pseudoHead8.setY(snake_head.getY() - 1);

        }


        if (gameOver || snake_head == null) return Direction.UP;
        // your code must be here!

        Optional<List<LeePoint>> solution_apple = lee.trace(snake_head, apple, obstacles);
        Optional<List<LeePoint>> pseuodUL = lee.trace(snake_head, pseudoAppleUL, obstacles);
        Optional<List<LeePoint>> pseuodUR = lee.trace(snake_head, pseudoAppleUR, obstacles);
        Optional<List<LeePoint>> pseuodDL = lee.trace(snake_head, pseudoAppleDL, obstacles);
        Optional<List<LeePoint>> pseuodDR = lee.trace(snake_head, pseudoAppleDR, obstacles);
        Optional<List<LeePoint>> pseuodHead01 = lee.trace(snake_head, pseudoHead1, obstacles);
        Optional<List<LeePoint>> pseuodHead02 = lee.trace(snake_head, pseudoHead2, obstacles);
        Optional<List<LeePoint>> pseuodHead03 = lee.trace(snake_head, pseudoHead3, obstacles);
        Optional<List<LeePoint>> pseuodHead04 = lee.trace(snake_head, pseudoHead4, obstacles);
        Optional<List<LeePoint>> pseuodHead05 = lee.trace(snake_head, pseudoHead5, obstacles);
        Optional<List<LeePoint>> pseuodHead06 = lee.trace(snake_head, pseudoHead6, obstacles);
        Optional<List<LeePoint>> pseuodHead07 = lee.trace(snake_head, pseudoHead7, obstacles);
        Optional<List<LeePoint>> pseuodHead08 = lee.trace(snake_head, pseudoHead8, obstacles);

        Optional<List<LeePoint>> tailSnake = lee.trace(snake_head, tail, obstacles);

        if (tailSnake.isPresent()&&solution_apple.isPresent()){
            if (tailSnake.get().size()<solution_apple.get().size()&&obstacles.size()>100){
                System.out.println("Tail is nearer than appple");
                return coord_to_direction(snake_head, tailSnake.get().get(1));
            }
        }
/*
        if (solution_apple.isPresent()){
            Point temp=snake_head.copy();
            ArrayList<Point> pseudoObstacles = new ArrayList<>();
            List<Point>pseudoTailList = new ArrayList<>();
            pseudoTailList.addAll(tailList);
            pseudoObstacles.addAll(obstacles);
            pseudoObstacles.remove(stone);
            pseudoObstacles.remove(snake_head);
            pseudoObstacles.remove(tailList);
            pseudoObstacles.remove(apple);
            System.out.println("solution apple" + solution_apple);
            for (int i = 0; i <pseudoTailList.size(); i++) {
                System.out.println(solution_apple.get().size());
                temp.setX(solution_apple.get().get(i).x());
                temp.setY(solution_apple.get().get(i).y());
                System.out.println("TailList=" + tailList);
               System.out.println("Temp= " +temp);
                pseudoTailList.add(null);
                pseudoTailList.get(pseudoTailList.size()-1).setX(solution_apple.get().get(i).x());
                pseudoTailList.get(pseudoTailList.size()-1).setY(solution_apple.get().get(i).y());
                pseudoTailList.remove(pseudoTailList.get(0));
                System.out.println("<<<<<<<<<");
                System.out.println(pseudoTailList);

            }
            pseudoObstacles.addAll(pseudoTailList);

            System.out.println("Pseduo Obstacles" + pseudoObstacles);
            System.out.println("Pseudo Tailist= " + pseudoTailList );

*//*
            for (int i = 0; i <solution_apple.get().size() ; i++) {
               //System.out.println("Calculation opf new way");
                for (int j = tailList.size()-1-i; j <tailList.size()&&j>0 ; j++) {
                    //obstacles.remove(tailList.get(j));
                    //tailList.remove(tailList.get(j));
                    Point fakeHead=tail.copy();
                    fakeHead.setX(solution_apple.get().get(i).x());
                    fakeHead.setY(solution_apple.get().get(i).y());
                    obstacles.add(fakeHead);
                }
//                if (tailList.isEmpty()){
//                    System.out.println("EmptyEmpty");
//                }
            }*//*
            Optional<List<LeePoint>> isWayOut = lee.trace(temp, stone, pseudoObstacles);
            if (isWayOut.isPresent()){
                System.out.println("THERE IS A WAY");
               return coord_to_direction(snake_head,solution_apple.get().get(1));
            }
            else {
                System.out.println("NNNNNOOOOO  WWWAAAAAAYYYYY");
            }
        }*/



        if (solution_apple.isPresent()) {
            return coord_to_direction(snake_head, solution_apple.get().get(1));
        }

        obstacles.remove(tail);
        if (tailSnake.isPresent()) {
            System.out.println("TAAAAAAAAAIIIIIIILLLLLLLLL");
            return coord_to_direction(snake_head, tailSnake.get().get(1));
        }


        if (pseuodUL.isPresent()) {
            System.out.println("UUUUUUUUUUUUUUUUUULLLLLLLLLLLLLLLLLLLLLLLL");
            return coord_to_direction(snake_head, pseuodUL.get().get(1));
        }
        if (pseuodUR.isPresent()) {
            System.out.println("UUUUUUUUUUUUUUUUUUUUUUUURRRRRRRRRRRRRRRRRRRRRR");
            return coord_to_direction(snake_head, pseuodUR.get().get(1));
        }
        if (pseuodDL.isPresent()) {
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDLLLLLLLLLLLLLLLLLL");
            return coord_to_direction(snake_head, pseuodDL.get().get(1));
        }
        if (pseuodDR.isPresent()) {
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDRRRRRRRRRRRRRRRRRR");
            return coord_to_direction(snake_head, pseuodDR.get().get(1));
        }

        if (pseuodHead01.isPresent()) {
            System.out.println("111111111111111111111111111");
            return coord_to_direction(snake_head, pseuodHead01.get().get(1));
        }

        if (pseuodHead02.isPresent()) {
            System.out.println("2222222222222222222222222222");
            return coord_to_direction(snake_head, pseuodHead02.get().get(1));
        }

        if (pseuodHead03.isPresent()) {
            System.out.println("333333333333333333333333333");
            return coord_to_direction(snake_head, pseuodHead03.get().get(1));
        }

        if (pseuodHead04.isPresent()) {
            System.out.println("4444444444444444444444444");
            return coord_to_direction(snake_head, pseuodHead04.get().get(1));
        }

        if (pseuodHead05.isPresent()) {
            System.out.println("55555555555555555555555555555");
            return coord_to_direction(snake_head, pseuodHead05.get().get(1));
        }

        if (pseuodHead06.isPresent()) {
            System.out.println("6666666666666666666666666666");
            return coord_to_direction(snake_head, pseuodHead06.get().get(1));
        }

        if (pseuodHead07.isPresent()) {
            System.out.println("777777777777777777777777777");
            return coord_to_direction(snake_head, pseuodHead07.get().get(1));
        }

        if (pseuodHead08.isPresent()) {
            System.out.println("8888888888888888888888888888888");
            return coord_to_direction(snake_head, pseuodHead08.get().get(1));
        }


        obstacles.remove(stone);
        Optional<List<LeePoint>> stoneT = lee.trace(snake_head, stone, obstacles);
        if (stoneT.isPresent()) {
            System.out.println("STONE STONE STONE STONE STONE STONE STONE STONE ");
            return coord_to_direction(snake_head, stoneT.get().get(1));
        }

        System.out.println("END END END END END END ");
        return Direction.UP;

    }

    private Direction coord_to_direction(Point from, LeePoint to) {
        if (to.x() < from.getX()) return Direction.LEFT;
        if (to.x() > from.getX()) return Direction.RIGHT;
        if (to.y() > from.getY()) return Direction.UP;   // vise versa because of reverted board
        if (to.y() < from.getY()) return Direction.DOWN; // vise versa because of reverted board
        throw new RuntimeException("you shouldn't be there...");
    }

}
