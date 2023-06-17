package com.lutf.staff

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lutf.staff.model.DataItem
import com.lutf.staff.presenter.CrudView
import com.lutf.staff.presenter.Presenter2
import kotlinx.android.synthetic.main.activity_update_add.*

class UpdateAddActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter2: Presenter2
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)

        presenter2 = Presenter2(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")

        if (itemDataItem == null) {
            btnAction.text = "Tambah"
            btnAction.setOnClickListener() {
                presenter2.addData(
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAddress.text.toString()
                )
            }
        } else if (itemDataItem != null) {
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etName.setText(item?.staffName.toString())
            etPhone.setText(item?.staffHp.toString())
            etAddress.setText(item?.staffAlamat.toString())
            btnAction.setOnClickListener() {
                presenter2.updatedata(
                    item?.staffId ?:"",
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAddress.text.toString()
                )
                finish()
            }
        }
    }

    override fun onSuccessGet(data: List<DataItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGet(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorAdd(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdate(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorUpdate(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDelete(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDelete(msg: String) {
        TODO("Not yet implemented")
    }
}