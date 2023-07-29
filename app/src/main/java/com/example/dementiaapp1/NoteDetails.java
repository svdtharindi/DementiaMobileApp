package com.example.dementiaapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NoteDetails extends AppCompatActivity {
    EditText titleEditText,contentEditText;
    ImageButton saveNoteBtn;

    /**********************************/
    TextView pageTitleTextView;
    String title,content,docID;
    boolean isEditMode=false;
    TextView deleteNoteTextViewBtn;
    /**********************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        titleEditText=findViewById(R.id.notes_title_text);
        contentEditText=findViewById(R.id.notes_content_text);
        saveNoteBtn=findViewById(R.id.save_note_btn);
        /**********************************/
        pageTitleTextView=findViewById(R.id.page_title);
        deleteNoteTextViewBtn=findViewById(R.id.dlete_note_text_view_btn);
        //receive data
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        docID=getIntent().getStringExtra("docID");

        if(docID!=null && !docID.isEmpty()){
            isEditMode=true;
        }

        titleEditText.setText(title);
        contentEditText.setText(content);

        if(isEditMode){
            pageTitleTextView.setText("Edit your note");
            deleteNoteTextViewBtn.setVisibility(View.VISIBLE);
        }
        /**********************************/



        saveNoteBtn.setOnClickListener((v)-> saveNote());
        /**********************************/
        deleteNoteTextViewBtn.setOnClickListener((v)->deleteNoteFromFirebase());
        /**********************************/
    }

    void saveNote(){
        String noteTitle =titleEditText.getText().toString();
        String noteContent =contentEditText.getText().toString();
        if(noteTitle==null || noteTitle.isEmpty()){
            titleEditText.setError("Title is required");
            return;
        }
        Note note =new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);
    }

    void saveNoteToFirebase(Note note){
        DocumentReference documentReference;
        if(isEditMode){
            //update the note
            documentReference=Utility.getCollectionReferenceForNotes().document(docID);
        }else {
            //create new note
            documentReference=Utility.getCollectionReferenceForNotes().document();
        }
        //documentReference=Utility.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //note is added
                    Utility.showToast(NoteDetails.this,"Note added successfully");
                    finish();
                }else{
                    Utility.showToast(NoteDetails.this,"Failed adding note");
                }
            }
        });
    }
    /**********************************/
    void deleteNoteFromFirebase(){
        DocumentReference documentReference;

        documentReference=Utility.getCollectionReferenceForNotes().document(docID);

        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //note is deleted
                    Utility.showToast(NoteDetails.this,"Note deleted successfully");
                    finish();
                }else{
                    Utility.showToast(NoteDetails.this,"Failed to delete note");
                }
            }
        });
    }
    /**********************************/
}