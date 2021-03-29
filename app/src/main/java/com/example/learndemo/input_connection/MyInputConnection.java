package com.example.learndemo.input_connection;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description:
 * Author: TianShuxin
 * Date: 2020/10/20
 */
class MyInputConnection implements InputConnection {
    private static final String TAG = MyInputConnection.class.getName();
    private CEditText editText;
    private Handler handler;

    public MyInputConnection(CEditText editText, Handler handler) {
        this.editText = editText;
        this.handler = handler;
    }

    @Override
    public CharSequence getTextBeforeCursor(int n, int flags) {
        Log.d(TAG, "MyInputConnection#getTextBeforeCursor: " + n + ", " + flags);
        return null;
    }

    @Override
    public CharSequence getTextAfterCursor(int n, int flags) {
        Log.d(TAG, "MyInputConnection#getTextAfterCursor: " + n + ", " + flags);
        return null;
    }

    @Override
    public CharSequence getSelectedText(int flags) {
        Log.d(TAG, "MyInputConnection#getSelectedText: " + flags);
        return null;
    }

    @Override
    public int getCursorCapsMode(int reqModes) {
        Log.d(TAG, "MyInputConnection#getCursorCapsMode: " + reqModes);
        return 0;
    }

    @Override
    public ExtractedText getExtractedText(ExtractedTextRequest request, int flags) {
        Log.d(TAG, "MyInputConnection#getExtractedText: " + request.toString() + ", " + flags);
        return null;
    }

    @Override
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        Log.d(TAG, "MyInputConnection#deleteSurroundingText: " + beforeLength + ", " + afterLength);
        return true;
    }

    @Override
    public boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) {
        Log.d(TAG, "MyInputConnection#deleteSurroundingTextInCodePoints: " + beforeLength + ", " + afterLength);
        return true;
    }

    @Override
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        Log.d(TAG, "MyInputConnection#setComposingText: " + text);
        return true;
    }

    @Override
    public boolean setComposingRegion(int start, int end) {
        Log.d(TAG, "MyInputConnection#setComposingRegion: " + start + ", " + end);
        return true;
    }

    @Override
    public boolean finishComposingText() {
        Log.d(TAG, "MyInputConnection#finishComposingText: ");
        return true;
    }

    @Override
    public boolean commitText(CharSequence text, int newCursorPosition) {
        Log.d(TAG, "MyInputConnection#commitText: " + text + ", " + newCursorPosition);
        editText.getEditableText().append(text);
        return true;
    }

    @Override
    public boolean commitCompletion(CompletionInfo text) {
        Log.d(TAG, "MyInputConnection#commitCompletion: " + text);
        return true;
    }

    @Override
    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        Log.d(TAG, "MyInputConnection#commitCorrection: " + correctionInfo);
        return true;
    }

    @Override
    public boolean setSelection(int start, int end) {
        Log.d(TAG, "MyInputConnection#setSelection: " + start + ", " + end);
        return true;
    }

    @Override
    public boolean performEditorAction(int editorAction) {
        Log.d(TAG, "MyInputConnection#performEditorAction: " + editorAction);
        return true;
    }

    @Override
    public boolean performContextMenuAction(int id) {
        Log.d(TAG, "MyInputConnection#performContextMenuAction: " + id);
        return true;
    }

    @Override
    public boolean beginBatchEdit() {
        Log.d(TAG, "MyInputConnection#beginBatchEdit: ");
        return true;
    }

    @Override
    public boolean endBatchEdit() {
        Log.d(TAG, "MyInputConnection#endBatchEdit: ");
        return true;
    }

    @Override
    public boolean sendKeyEvent(KeyEvent event) {
        Log.d(TAG, "MyInputConnection#sendKeyEvent: " + event.getCharacters() + ", " + event.toString());
        return true;
    }

    @Override
    public boolean clearMetaKeyStates(int states) {
        return true;
    }

    @Override
    public boolean reportFullscreenMode(boolean enabled) {
        return true;
    }

    @Override
    public boolean performPrivateCommand(String action, Bundle data) {
        Log.d(TAG, "MyInputConnection#performPrivateCommand: " + data + ", " + data.toString());
        return true;
    }

    @Override
    public boolean requestCursorUpdates(int cursorUpdateMode) {
        Log.d(TAG, "MyInputConnection#requestCursorUpdates: " + cursorUpdateMode);
        return true;
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public void closeConnection() {
        Log.d(TAG, "MyInputConnection#closeConnection: ");
    }

    @Override
    public boolean commitContent(@NonNull InputContentInfo inputContentInfo, int flags, @Nullable Bundle opts) {
        Log.d(TAG, "MyInputConnection#commitContent: " + inputContentInfo.toString());
        return true;
    }
}
