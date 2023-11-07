package middle;

public class Subject2069 {

}

class Robot {

    int width;
    int height;
    int curWidth;
    int curHeight;
    String[] direction = new String[]{"East", "North", "West", "South"};
    int dirPoint;
    int sum;//到目前总的步数

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.curHeight = 0;
        this.curWidth = 0;
        this.dirPoint = 0;
        sum = 0;
    }

    public void step(int num) {
        sum += num;
        int num2 = sum % (2*(width + height -2));
        if (num2 <= width - 1) {
            curWidth = num2;
            curHeight = 0;
            if (num2 == 0 && num > 0) {
                dirPoint = 3;
            } else {
                dirPoint = 0;
            }
            return;
        }
        int num3 = num2 - width + 1;
        if (num3 <= height - 1) {
            curWidth = width - 1;
            curHeight = num3;
            dirPoint = 1;
            return;
        }
        int num4 = num3 - height + 1;
        if (num4 <= width - 1) {
            curWidth = width - 1 - num4;
            curHeight = height - 1;
            dirPoint = 2;
            return;
        }
        int num5 = num4 - width + 1;
        if (num5 <= height -1) {
            curHeight = height - 1 - num5;
            curWidth = 0;
            dirPoint = 3;
            return;
        } else {
            System.err.println("之前某一步出了问题！");
        }
    }

    public int[] getPos() {
        return new int[]{curWidth, curHeight};
    }

    public String getDir() {
        return direction[dirPoint];
    }

    public void certainDir2(){
        switch (dirPoint){
            case 0:
                if (++curWidth >= width) {
                    curWidth--;
                    dirPoint=1;
                    certainDir();
                    break;
                } else {return;}
            case 1:
                if (++curHeight >= height) {
                    curHeight--;
                    dirPoint=2;
                    certainDir();
                    break;
                } else {return;}
            case 2:
                if (--curWidth < 0) {
                    curWidth++;
                    dirPoint=3;
                    certainDir();
                    break;
                } else {return;}
            case 3:
                if (--curHeight < 0) {
                    curHeight++;
                    dirPoint=0;
                    certainDir();
                    break;
                } else {return;}
            default:
                System.out.println("数组越界出错");
        }
    }

    public void certainDir(){
        while (true) {
            switch (dirPoint) {
                case 0:
                    if (++curWidth >= width) {
                        curWidth--;
                        dirPoint = 1;
                        break;
                    } else {
                        return;
                    }
                case 1:
                    if (++curHeight >= height) {
                        curHeight--;
                        dirPoint = 2;
                        break;
                    } else {
                        return;
                    }
                case 2:
                    if (--curWidth < 0) {
                        curWidth++;
                        dirPoint = 3;
                        break;
                    } else {
                        return;
                    }
                case 3:
                    if (--curHeight < 0) {
                        curHeight++;
                        dirPoint = 0;
                        break;
                    } else {
                        return;
                    }
                default:
                    System.out.println("数组越界出错");
            }
        }
    }

    public void certainDir3(){

    }
}
