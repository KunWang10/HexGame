package HexGame;

import java.util.ArrayList;

/**
 * Created by kun on 2017/11/07.
 * <p>
 * This class is defining the definition of node, giving the node method to define direction,distance,and so on
 */
public class Node {

    private int id;

    public int getId() {
        return id;
    }

    private int row;

    public int getRow() {
        return row;
    }

    public static int getRow(int id) {
        Node[] nodes = new Node[217];
        Node.create(nodes);
        return nodes[id].getRow();
    }

    private int column;

    public int getColumn() {
        return column;
    }

    public static int getColumn(int id) {
        Node[] nodes = new Node[217];
        Node.create(nodes);
        return nodes[id].getColumn();
    }

    public Node(int id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }


    public static boolean isBoundary(int id) {
        return (id >= 169 && id <= 216);
    }


    //judging whether the step is in a isLine
    public static boolean isLine(int from, int to) {
        Node[] id = new Node[217];
        Node.create(id);
        int rf = id[from].getRow();
        int rt = id[to].getRow();
        int cf = id[from].getColumn();
        int ct = id[to].getColumn();

        return rf == rt || cf == ct || rf - rt == cf - ct;
    }

    public static int getId(int row, int column) {
        Node[] id = new Node[217];
        Node.create(id);
        for (int i = 0; i < 217; i++) {
            if (id[i].getRow() == row && id[i].getColumn() == column) {
                return i;
            }
        }
        return -1;
    }

    //defining the direction of the step
    public static int direction(int from, int to) {

        if (!isLine(from, to)) {
            return -1;
        }
        Node[] id = new Node[217];
        Node.create(id);
        int drow = id[to].getRow() - id[from].getRow();
        int dcolumn = id[to].getColumn() - id[from].getColumn();
        if (drow < 0 && dcolumn < 0) {
            return 1;
        } else if (drow < 0 && dcolumn == 0) {
            return 2;
        } else if (drow == 0 && dcolumn > 0) {
            return 3;
        } else if (drow > 0 && dcolumn > 0) {
            return 4;
        } else if (drow > 0 && dcolumn == 0) {
            return 5;
        } else if (drow == 0 && dcolumn < 0) {
            return 6;
        } else {
            return -1;
        }
    }


    // defining the perimeter
    public static String perimeter(int n) {

        if (169 < n && n < 177) {
            return "01";
        } else if (177 < n && n < 185) {
            return "02";
        } else if (185 < n && n < 193) {
            return "03";
        } else if (193 < n && n < 201) {
            return "04";
        } else if (201 < n && n < 209) {
            return "05";
        } else if (209 < n && n < 216) {
            return "06";
        } else if (n == 169) {
            return "16";
        } else if (n == 177) {
            return "12";
        } else if (n == 185) {
            return "23";
        } else if (n == 193) {
            return "34";
        } else if (n == 201) {
            return "45";
        } else if (n == 209) {
            return "56";
        }
        {
            return "-1";
        }

    }

    //judging whether is a cranny or not
    public static boolean cra(String game, int id) {
        int[] cras = new int[6];
        Node.createCra(game, cras);
        for (int i = 0; i < 6; i++) {
            if (id == cras[i]) {
                return true;
            }
        }
        return false;
    }


    //computing the distance of a step
    public static int distance(int from, int to) {
        if (!isLine(from, to)) {
            return -1;
        }
        Node[] id = new Node[217];
        Node.create(id);
        int drow = Math.abs(id[to].getRow() - id[from].getRow());
        int dcolumn = Math.abs(id[to].getColumn() - id[from].getColumn());
        return Math.max(drow, dcolumn);
    }

    //getting the next HexGame.Node by the giving direction
    public static int next(int from, int drc) {
        Node[] id = new Node[217];
        Node.create(id);
        int rf = id[from].getRow();
        int cf = id[from].getColumn();
        int rt = -1;
        int ct = -1;
        if (drc == 1) {
            rt = rf - 1;
            ct = cf - 1;
        } else if (drc == 2) {
            rt = rf - 1;
            ct = cf;
        } else if (drc == 3) {
            rt = rf;
            ct = cf + 1;
        } else if (drc == 4) {
            rt = rf + 1;
            ct = cf + 1;
        } else if (drc == 5) {
            rt = rf + 1;
            ct = cf;
        } else if (drc == 6) {
            rt = rf;
            ct = cf - 1;
        }
        return getId(rt, ct);

    }

    //getting direction by the giving nook
    public static char getDrc(String game, int id) {
        int[] nks = new int[18];
        Node.createNks(game, nks);
        char[] psn = new char[18];
        Node.createPsn(game, psn);
        for (int i = 0; i < 18; i++) {
            if (id == nks[i]) {
                return psn[i];
            }
        }
        return 'X';
    }

    //creating 6 crannies
    public static void createCra(String game, int[] cra) {
        for (int i = 0; i < 6; i++) {
            cra[i] = Integer.parseInt(game.substring(3 * i, 3 * i + 3));
        }
    }

    //creating 18 nooks
    public static void createNks(String game, int[] nks) {
        for (int i = 0; i < 18; i++) {
            nks[i] = Integer.parseInt(game.substring(4 * i + 18, 4 * i + 21));
        }
    }

    //creating pieces
    public static void createPsn(String game, char[] psn) {
        for (int i = 0; i < 18; i++) {
            psn[i] = game.charAt(4 * i + 21);
        }
    }


    public static boolean cout(String game, int id, int drc) {
        int[] cra = new int[6];
        Node.createCra(game, cra);

        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (cra[i] > cra[j]) {
                    int k = cra[i];
                    cra[i] = cra[j];
                    cra[j] = k;
                }
            }
        }

        if (id == cra[0] && (drc == 4 || drc == 5 || drc == 6)) {
            return true;
        } else if (id == cra[0] + 1 && (drc == 3 || drc == 4 || drc == 5)) {
            return true;
        } else if (id == cra[1] && (drc == 1 || drc == 5 || drc == 6)) {
            return true;
        } else if (id == cra[1] + 1 && (drc == 4 || drc == 5 || drc == 6)) {
            return true;
        } else if (id == cra[2] && (drc == 1 || drc == 2 || drc == 6)) {
            return true;
        } else if (id == cra[2] + 1 && (drc == 1 || drc == 5 || drc == 6)) {
            return true;
        } else if (id == cra[3] && (drc == 1 || drc == 2 || drc == 3)) {
            return true;
        } else if (id == cra[3] + 1 && (drc == 1 || drc == 2 || drc == 6)) {
            return true;
        } else if (id == cra[4] && (drc == 2 || drc == 3 || drc == 4)) {
            return true;
        } else if (id == cra[4] + 1 && (drc == 1 || drc == 2 || drc == 3)) {
            return true;
        } else if (id == cra[5] && (drc == 3 || drc == 4 || drc == 5)) {
            return true;
        } else if (id == cra[5] + 1 && (drc == 2 || drc == 3 || drc == 4)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean c(String game, int id) {
        int[] cra = new int[6];
        Node.createCra(game, cra);
        for (int i = 0; i < 6; i++) {
            if (id == cra[i] || id == cra[i] + 1)
                return true;
        }
        return false;
    }

    // judging whether the piece can get out from the border or not y giving argument id and direction
    public static boolean sideout(int id, int drc) {
        if (id >= 170 && id <= 176 && (drc == 3 || drc == 4 || drc == 5 || drc == 6)) {
            return true;
        } else if (id >= 178 && id <= 184 && (drc == 1 || drc == 4 || drc == 5 || drc == 6)) {
            return true;
        } else if (id >= 186 && id <= 192 && (drc == 1 || drc == 2 || drc == 5 || drc == 6)) {
            return true;
        } else if (id >= 194 && id <= 200 && (drc == 1 || drc == 2 || drc == 3 || drc == 6)) {
            return true;
        } else if (id >= 202 && id <= 208 && (drc == 1 || drc == 2 || drc == 3 || drc == 4)) {
            return true;
        } else if (id >= 210 && id <= 216 && (drc == 2 || drc == 3 || drc == 4 || drc == 5)) {
            return true;
        }
        return false;
    }

    // judging whether the piece can get out from the angle or not by giving argument id and direction
    public static boolean angout(int id, int drc) {

        if (id == 169 && (drc == 3 || drc == 4 || drc == 5)) {
            return true;
        } else if (id == 177 && (drc == 4 || drc == 5 || drc == 6)) {
            return true;
        } else if (id == 185 && (drc == 1 || drc == 5 || drc == 6)) {
            return true;
        } else if (id == 193 && (drc == 1 || drc == 2 || drc == 6)) {
            return true;
        } else if (id == 201 && (drc == 1 || drc == 2 || drc == 3)) {
            return true;
        } else if (id == 209 && (drc == 2 || drc == 3 || drc == 4)) {
            return true;
        }
        return false;
    }

    // judging whether is a angle or not by giving the argument id
    public static boolean isAngle(int id) {
        return (id == 169 || id == 177 || id == 185 || id == 193 || id == 201 || id == 209);
    }

    //judging whether is a piece or not by giving the arguments game and num
    public static boolean piece(String game, int num) {

        String pieces = game.substring(90);
        int n = pieces.length() / 3;
        ArrayList<Integer> piecess = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            piecess.add(Integer.parseInt(pieces.substring(3 * i, 3 * i + 3)));
        }
        return piecess.contains(num);
    }


    //This method is dealing with how much moves the piece can move
    public static int move(String game, int from, int drc) {
        int move = Node.next(from, drc);
        int record = from;
        //start from an angle
        if (Node.isAngle(from)) {
            if (!angout(from, drc)) {
                return from;
            }
            if (Node.c(game, from) && Node.c(game, move)) {
                return from;
            }
            if (Node.piece(game, move)) {
                return record;
            }
            if (Node.isBoundary(move)) {
                if (Node.c(game, move)) {
                    return move;
                }
                do {
                    record = move;
                    move = Node.next(move, drc);
                    if (Node.piece(game, move)) {
                        return record;
                    }
                    if (Node.c(game, move)) {
                        return move;
                    }
                } while (true);
            } else {
                do {
                    record = move;
                    move = Node.next(move, drc);
                    if (Node.piece(game, move)) {
                        return record;
                    }
                    if (Node.isAngle(move)) {
                        return move;
                    }
                } while (true);
            }
        }
        //start at a cranny
        if (Node.c(game, from)) {
            if (!cout(game, from, drc)) {
                return from;
            }
            if (Node.piece(game, move)) {
                return record;
            }
            if (Node.isBoundary(move)) {
                do {
                    if (Node.isAngle(move)) {
                        return move;
                    }
                    record = move;
                    move = Node.next(move, drc);
                    if (Node.piece(game, move)) {
                        return record;
                    }
                } while (true);
            }
            do {
                record = move;
                move = Node.next(move, drc);
                if (Node.piece(game, move)) {
                    return record;
                }
                if (Node.isBoundary(move)) {
                    return move;
                }
                if ((Nook.nk(game, move)) && !Nook.out(Node.getDrc(game, move), drc)) {
                    return move;
                }
                if ((Nook.nk(game, move)) && Nook.out(Node.getDrc(game, move), drc)) {
                    return record;
                }
            } while (true);

        }
        //start from a side-point
        if (Node.isBoundary(from)) {
            if (!Node.sideout(from, drc)) {
                return from;
            }
            if (Node.piece(game, move)) {
                return record;
            }
            if (Node.isBoundary(move)) {
                do {
                    if (Node.c(game, move) || Node.isAngle(move)) {
                        return move;
                    }
                    record = move;
                    move = Node.next(move, drc);
                    if (Node.piece(game, move)) {
                        return record;
                    }
                } while (true);
            }
            do {
                record = move;
                move = Node.next(move, drc);
                if (Node.piece(game, move)) {
                    return record;
                }
                if (Node.isBoundary(move)) {
                    return move;
                }
                if (Node.c(game, move)) {
                    return move;
                }
                if (Node.isAngle(move)) {
                    return move;
                }
                if ((Nook.nk(game, move)) && !Nook.out(Node.getDrc(game, move), drc)) {
                    return move;
                }
                if ((Nook.nk(game, move)) && Nook.out(Node.getDrc(game, move), drc)) {
                    return record;
                }
            } while (true);

        }
        if (Nook.nk(game, from)) {
            if (Nook.out(Node.getDrc(game, from), drc)) {
                if (Node.piece(game, move)) {
                    return record;
                }
                do {
                    record = move;
                    move = Node.next(move, drc);
                    if (Node.piece(game, move)) {
                        return record;
                    }
                    if (Node.isBoundary(move)) {
                        return move;
                    }
                    if (Node.c(game, move)) {
                        return move;
                    }
                    if (Node.isAngle(move)) {
                        return move;
                    }
                    if ((Nook.nk(game, move)) && !Nook.out(Node.getDrc(game, move), drc)) {
                        return move;
                    }
                    if ((Nook.nk(game, move)) && Nook.out(Node.getDrc(game, move), drc)) {
                        return record;
                    }
                } while (true);
            }
        } else {
            if (Node.piece(game, move)) {
                return record;
            }
            if (Node.isBoundary(move)) {
                return move;
            }
            if (Node.c(game, move)) {
                return move;
            }
            if (Node.isAngle(move)) {
                return move;
            }
            if ((Nook.nk(game, move)) && !Nook.out(Node.getDrc(game, move), drc)) {
                return move;
            }
            if ((Nook.nk(game, move)) && Nook.out(Node.getDrc(game, move), drc)) {
                return record;
            }
            do {
                record = move;
                move = Node.next(move, drc);
                if (Node.piece(game, move)) {
                    return record;
                }
                if (Node.isBoundary(move)) {
                    return move;
                }
                if (Node.c(game, move)) {
                    return move;
                }
                if (Node.isAngle(move)) {
                    return move;
                }
                if ((Nook.nk(game, move)) && !Nook.out(Node.getDrc(game, move), drc)) {
                    return move;
                }
                if ((Nook.nk(game, move)) && Nook.out(Node.getDrc(game, move), drc)) {
                    return record;
                }
            } while (true);
        }
        return from;
    }

    //creating exactly id of the node
    // TODO: 7/11/2017 Not elegant, optimise this.

    public static void create(Node[] id) {
        id[169] = new Node(169, 1, 1);
        id[170] = new Node(170, 1, 2);
        id[171] = new Node(171, 1, 3);
        id[172] = new Node(172, 1, 4);
        id[173] = new Node(173, 1, 5);
        id[174] = new Node(174, 1, 6);
        id[175] = new Node(175, 1, 7);
        id[176] = new Node(176, 1, 8);
        id[177] = new Node(177, 1, 9);

        id[216] = new Node(216, 2, 1);
        id[127] = new Node(127, 2, 2);
        id[128] = new Node(128, 2, 3);
        id[129] = new Node(129, 2, 4);
        id[130] = new Node(130, 2, 5);
        id[131] = new Node(131, 2, 6);
        id[132] = new Node(132, 2, 7);
        id[133] = new Node(133, 2, 8);
        id[134] = new Node(134, 2, 9);
        id[178] = new Node(178, 2, 10);

        id[215] = new Node(215, 3, 1);
        id[168] = new Node(168, 3, 2);
        id[91] = new Node(91, 3, 3);
        id[92] = new Node(92, 3, 4);
        id[93] = new Node(93, 3, 5);
        id[94] = new Node(94, 3, 6);
        id[95] = new Node(95, 3, 7);
        id[96] = new Node(96, 3, 8);
        id[97] = new Node(97, 3, 9);
        id[135] = new Node(135, 3, 10);
        id[179] = new Node(179, 3, 11);

        id[214] = new Node(214, 4, 1);
        id[167] = new Node(167, 4, 2);
        id[126] = new Node(126, 4, 3);
        id[61] = new Node(61, 4, 4);
        id[62] = new Node(62, 4, 5);
        id[63] = new Node(63, 4, 6);
        id[64] = new Node(64, 4, 7);
        id[65] = new Node(65, 4, 8);
        id[66] = new Node(66, 4, 9);
        id[98] = new Node(98, 4, 10);
        id[136] = new Node(136, 4, 11);
        id[180] = new Node(180, 4, 12);

        id[213] = new Node(213, 5, 1);
        id[166] = new Node(166, 5, 2);
        id[125] = new Node(125, 5, 3);
        id[90] = new Node(90, 5, 4);
        id[37] = new Node(37, 5, 5);
        id[38] = new Node(38, 5, 6);
        id[39] = new Node(39, 5, 7);
        id[40] = new Node(40, 5, 8);
        id[41] = new Node(41, 5, 9);
        id[67] = new Node(67, 5, 10);
        id[99] = new Node(99, 5, 11);
        id[137] = new Node(137, 5, 12);
        id[181] = new Node(181, 5, 13);

        id[212] = new Node(212, 6, 1);
        id[165] = new Node(165, 6, 2);
        id[124] = new Node(124, 6, 3);
        id[89] = new Node(89, 6, 4);
        id[60] = new Node(60, 6, 5);
        id[19] = new Node(19, 6, 6);
        id[20] = new Node(20, 6, 7);
        id[21] = new Node(21, 6, 8);
        id[22] = new Node(22, 6, 9);
        id[42] = new Node(42, 6, 10);
        id[68] = new Node(68, 6, 11);
        id[100] = new Node(100, 6, 12);
        id[138] = new Node(138, 6, 13);
        id[182] = new Node(182, 6, 14);

        id[211] = new Node(211, 7, 1);
        id[164] = new Node(164, 7, 2);
        id[123] = new Node(123, 7, 3);
        id[88] = new Node(88, 7, 4);
        id[59] = new Node(59, 7, 5);
        id[36] = new Node(36, 7, 6);
        id[7] = new Node(7, 7, 7);
        id[8] = new Node(8, 7, 8);
        id[9] = new Node(9, 7, 9);
        id[23] = new Node(23, 7, 10);
        id[43] = new Node(43, 7, 11);
        id[69] = new Node(69, 7, 12);
        id[101] = new Node(101, 7, 13);
        id[139] = new Node(139, 7, 14);
        id[183] = new Node(183, 7, 15);

        id[210] = new Node(210, 8, 1);
        id[163] = new Node(163, 8, 2);
        id[122] = new Node(122, 8, 3);
        id[87] = new Node(87, 8, 4);
        id[58] = new Node(58, 8, 5);
        id[35] = new Node(35, 8, 6);
        id[18] = new Node(18, 8, 7);
        id[1] = new Node(1, 8, 8);
        id[2] = new Node(2, 8, 9);
        id[10] = new Node(10, 8, 10);
        id[24] = new Node(24, 8, 11);
        id[44] = new Node(44, 8, 12);
        id[70] = new Node(70, 8, 13);
        id[102] = new Node(102, 8, 14);
        id[140] = new Node(140, 8, 15);
        id[184] = new Node(184, 8, 16);

        id[209] = new Node(209, 9, 1);
        id[162] = new Node(162, 9, 2);
        id[121] = new Node(121, 9, 3);
        id[86] = new Node(86, 9, 4);
        id[57] = new Node(57, 9, 5);
        id[34] = new Node(34, 9, 6);
        id[17] = new Node(17, 9, 7);
        id[6] = new Node(6, 9, 8);
        id[0] = new Node(0, 9, 9);
        id[3] = new Node(3, 9, 10);
        id[11] = new Node(11, 9, 11);
        id[25] = new Node(25, 9, 12);
        id[45] = new Node(45, 9, 13);
        id[71] = new Node(71, 9, 14);
        id[103] = new Node(103, 9, 15);
        id[141] = new Node(141, 9, 16);
        id[185] = new Node(185, 9, 17);

        id[208] = new Node(208, 10, 2);
        id[161] = new Node(161, 10, 3);
        id[120] = new Node(120, 10, 4);
        id[85] = new Node(85, 10, 5);
        id[56] = new Node(56, 10, 6);
        id[33] = new Node(33, 10, 7);
        id[16] = new Node(16, 10, 8);
        id[5] = new Node(5, 10, 9);
        id[4] = new Node(4, 10, 10);
        id[12] = new Node(12, 10, 11);
        id[26] = new Node(26, 10, 12);
        id[46] = new Node(46, 10, 13);
        id[72] = new Node(72, 10, 14);
        id[104] = new Node(104, 10, 15);
        id[142] = new Node(142, 10, 16);
        id[186] = new Node(186, 10, 17);

        id[207] = new Node(207, 11, 3);
        id[160] = new Node(160, 11, 4);
        id[119] = new Node(119, 11, 5);
        id[84] = new Node(84, 11, 6);
        id[55] = new Node(55, 11, 7);
        id[32] = new Node(32, 11, 8);
        id[15] = new Node(15, 11, 9);
        id[14] = new Node(14, 11, 10);
        id[13] = new Node(13, 11, 11);
        id[27] = new Node(27, 11, 12);
        id[47] = new Node(47, 11, 13);
        id[73] = new Node(73, 11, 14);
        id[105] = new Node(105, 11, 15);
        id[143] = new Node(143, 11, 16);
        id[187] = new Node(187, 11, 17);

        id[206] = new Node(206, 12, 4);
        id[159] = new Node(159, 12, 5);
        id[118] = new Node(118, 12, 6);
        id[83] = new Node(83, 12, 7);
        id[54] = new Node(54, 12, 8);
        id[31] = new Node(31, 12, 9);
        id[30] = new Node(30, 12, 10);
        id[29] = new Node(29, 12, 11);
        id[28] = new Node(28, 12, 12);
        id[48] = new Node(48, 12, 13);
        id[74] = new Node(74, 12, 14);
        id[106] = new Node(106, 12, 15);
        id[144] = new Node(144, 12, 16);
        id[188] = new Node(188, 12, 17);

        id[205] = new Node(205, 13, 5);
        id[158] = new Node(158, 13, 6);
        id[117] = new Node(117, 13, 7);
        id[82] = new Node(82, 13, 8);
        id[53] = new Node(53, 13, 9);
        id[52] = new Node(52, 13, 10);
        id[51] = new Node(51, 13, 11);
        id[50] = new Node(50, 13, 12);
        id[49] = new Node(49, 13, 13);
        id[75] = new Node(75, 13, 14);
        id[107] = new Node(107, 13, 15);
        id[145] = new Node(145, 13, 16);
        id[189] = new Node(189, 13, 17);

        id[204] = new Node(204, 14, 6);
        id[157] = new Node(157, 14, 7);
        id[116] = new Node(116, 14, 8);
        id[81] = new Node(81, 14, 9);
        id[80] = new Node(80, 14, 10);
        id[79] = new Node(79, 14, 11);
        id[78] = new Node(78, 14, 12);
        id[77] = new Node(77, 14, 13);
        id[76] = new Node(76, 14, 14);
        id[108] = new Node(108, 14, 15);
        id[146] = new Node(146, 14, 16);
        id[190] = new Node(190, 14, 17);

        id[203] = new Node(203, 15, 7);
        id[156] = new Node(156, 15, 8);
        id[115] = new Node(115, 15, 9);
        id[114] = new Node(114, 15, 10);
        id[113] = new Node(113, 15, 11);
        id[112] = new Node(112, 15, 12);
        id[111] = new Node(111, 15, 13);
        id[110] = new Node(110, 15, 14);
        id[109] = new Node(109, 15, 15);
        id[147] = new Node(147, 15, 16);
        id[191] = new Node(191, 15, 17);

        id[202] = new Node(202, 16, 8);
        id[155] = new Node(155, 16, 9);
        id[154] = new Node(154, 16, 10);
        id[153] = new Node(153, 16, 11);
        id[152] = new Node(152, 16, 12);
        id[151] = new Node(151, 16, 13);
        id[150] = new Node(150, 16, 14);
        id[149] = new Node(149, 16, 15);
        id[148] = new Node(148, 16, 16);
        id[192] = new Node(192, 16, 17);

        id[201] = new Node(201, 17, 9);
        id[200] = new Node(200, 17, 10);
        id[199] = new Node(199, 17, 11);
        id[198] = new Node(198, 17, 12);
        id[197] = new Node(197, 17, 13);
        id[196] = new Node(196, 17, 14);
        id[195] = new Node(195, 17, 15);
        id[194] = new Node(194, 17, 16);
        id[193] = new Node(193, 17, 17);

    }


}
