package com.example.mycontact.data.source

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import com.example.mycontact.data.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetContact private constructor(private val contentResolver: ContentResolver?) {

    private var phoneNumber: String? = null
    private var uriImage: Uri? = null
    private var name: String? = null


    suspend fun queryAllContact(): MutableList<Contact> =
        withContext(Dispatchers.IO) {
            val listDanhbas = mutableListOf<Contact>()
            val cursor = contentResolver?.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
            )
            if (cursor!!.count > 0) {
                while (cursor.moveToNext()) {

                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val contactUri: Uri = ContentUris.withAppendedId(
                        ContactsContract.Contacts.CONTENT_URI,
                        id.toLong()
                    )
                    uriImage = Uri.withAppendedPath(
                        contactUri,
                        ContactsContract.Contacts.Photo.DISPLAY_PHOTO
                    )
                    val phoneCursor: Cursor? = contentResolver?.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id),
                        null
                    )
                    if (phoneCursor != null) {
                        if (phoneCursor.moveToNext()) {
                            phoneNumber =
                                phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            phoneCursor.close()
                        }
                    }

                    listDanhbas.add(Contact(phone = phoneNumber, name = name, uriImage = uriImage))
                }
            }
            listDanhbas
        }


    companion object {
        private var instance: GetContact? = null

        fun getInstance(contentResolver: ContentResolver?) = GetContact(contentResolver).also {
            instance = it
        }
    }
}