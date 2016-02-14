package org.qshp.commons.util.sort;

/**
 * Created by qinyong on 2016/2/5.
 * 排序
 */
public class CollectionsSort {

    /**
     * 快速排序
     * 假设我们现在对“6  1  2 7  9  3  4  5 10  8”这个10个数进行排序。
     * 首先在这个序列中随便找一个数作为基准数。
     * 为了方便，就让第一个数6作为基准数吧。
     * 接下来，需要将这个序列中所有比基准数大的数放在6的右边，
     * 比基准数小的数放在6的左边，类似下面这种排列：3  1  2  5  4  6  9 7  10  8
     * 在初始状态下，数字6在序列的第1位。
     * 我们的目标是将6挪到序列中间的某个位置，假设这个位置是k。
     * 现在就需要寻找这个k，并且以第k位为分界点，左边的数都小于等于6
     * ，右边的数都大于等于6
     * @param data
     * @param left
     * @param right
     */
    public static void quickSort(int[] data ,int left,int right){
        if(left >= right)
            return;
        //设置左边第一个元素为基准数
        int base = data[left];
        int i = left,j = right;
        while(i != j) {
            // 查找右边小于基准数
            while (data[j] >= base && i < j) {
                j--;
            }

            // 查找左边大于基准数
            while (data[i] <= base && i < j) {
                i++;
            }

            if (i < j) {
                swap(data,i,j);
            }
        }
        //基准数归位
        data[left] = data[i];
        data[i] = base;
        quickSort(data,left,i-1);
        quickSort(data, i + 1, right);
    }


    /**
     * 冒泡排序
     * @param data
     */
    public static void bubbleSort(int data[]){
        int n = data.length-1;
        for(int i = 0 ; i< n; i++) {
            // 右边最大值
            for(int j = 0; j < n-i; j++) {
                if(data[j] > data[j+1]) {
                    swap(data,j,j+1);
                }
            }
            // 或者以下for方式比较都可以 左边最小值
//            for(int j = i + 1; j < n; j++) {
//                if(data[i] > data[j]) {
//                    swap(data,i,j);
//                }
//            }
        }

    }

    /**
     * 改进后的冒泡排序
     * 对冒泡排序常见的改进方法是加入一标志性变量exchange，
     * 用于标志某一趟排序过程中是否有数据交换，如果进行某一趟排序时并没有进行数据交换，
     * 则说明数据已经按要求排列好，可立即结束排序，避免不必要的比较过程。
     * 改进算法：设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。
     * 由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可。
     * @param data
     */
    public static void bubbleSortForImprove(int data[]){
        int i = data.length-1 ,pos;
        while( i > 0){
            pos = 0;
            // 右边最大值
            for(int j = 0; j < i; j++) {
                if(data[j] > data[j+1]) {
                    swap(data,j,j+1);
                    pos = j;
                }
            }
            i = pos;
        }

    }

    /**
     * 数据交换
     * @param data
     * @param i
     * @param j
     */
    private static void swap(int[] data,int i,int j){
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }


    public static void main(String[] args) {
        int data[] = {1,3,7,4,5,9,8,3,0,1,2};
//        quickSort(data, 0, data.length-1);
        bubbleSortForImprove(data);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] +",");
        }
    }

}
