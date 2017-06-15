package com.mycompany.interviews;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class WordDictionary implements IWordDictionary, CompletionHandler<Integer, ByteBuffer> {

    List a;
    Consumer<List> callback;

    public void load(Consumer<List> callback) {
        this.callback = callback;

        Path p = Paths.get("assets/wordlist.txt");

        ByteBuffer buffer = ByteBuffer.allocate(9999999);
        AsynchronousFileChannel channel;
        try {
            channel = AsynchronousFileChannel.open(p);
            channel.read(buffer, 0, buffer, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] data = new byte[attachment.limit()];
        attachment.get(data);
        String strData = new String(data);
        a = Arrays.asList(strData.split("\n"));
        attachment.clear();
        callback.accept(a);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
    }

    public boolean contains(String word) {
        return a.indexOf(word) >= 0;
    }

    public int size() {
        return a.size();
    }

}