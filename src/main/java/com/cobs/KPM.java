package com.cobs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobs on 2018/7/29.
 * 串匹配算法
 * mainStr 表示主串
 * mode 表示查询串(匹配模式)
 * next 查询串中可跳转特性，记录查询串中某个位置i之前的字符串 头和尾最大偏移k记录长度，有modeArr[0] -- modeArr[k-1] = modeArr[j-k] -- modeArr[j-1] 记录next[j] = k;
 * 注意next记录的是偏移长度，j匹配的是其之前的字符串
 */
public class KPM {

    int[] getNext(String mode){
        if (mode == null){
            return null;
        }
        char[] modeArr = mode.toCharArray();
        int[] next = new int[modeArr.length];
        //index表示查询mode的位置，val对应的可偏移长度 0的时候默认是-1,1 的时候默认是 0
        int index = 0, val = -1;
        next[index] = val;
        while (index < modeArr.length - 1){
            if (val == -1){
                //如果val 等于-1 也就是index是0，设置1的偏移长度为0，相应的val 为0
                next[++index] = 0;
                val = 0;
            }else if (modeArr[index] == modeArr[val]){
                val++;
                next[++index] = val;
            }else {
                val = next[val];
            }
        }
        return next;
    }

    int kMP(String mainStr,String mode){
        if (mainStr == null || mode == null){
            return 0;
        }
        //i 记录mainStr位置 j记录 mode位置
        int i = 0 , j = 0;
        int[] next = getNext(mode);
        char[] modeArr = mode.toCharArray();
        char[] mainArr = mainStr.toCharArray();
        while (i < mainArr.length && j < modeArr.length){
            if (mainArr[i] == modeArr[j]){
                i++;j++;
            }else {
                j = next[j];
                if (j == -1){i++;j++;}
            }
        }
        if (j == modeArr.length)
            return  (i - modeArr.length + 1);
        else
            return 0;
    }

    @Test
    public void searchKmp(){
        String mainStr = "ababaababcb";
        String mode = "ababc";
        System.out.println(kMP(mainStr,mode)); //6 第六个
    }
}
