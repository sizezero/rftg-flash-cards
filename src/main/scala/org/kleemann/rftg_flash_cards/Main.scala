package org.kleemann.rftg_flash_cards

import org.scaloid.common._

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.os.{Bundle, Environment}
import android.provider.MediaStore
import android.util.Log
import android.view.{View, ViewGroup, Menu, MenuItem, Gravity}
import android.widget.{FrameLayout, Toast}

class Main extends SActivity  {

  onCreate {
    contentView = new SFrameLayout {

      this += new SVerticalLayout {

        SButton(R.string.hello)
	    .<<.wrap.Gravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL).>>
            .onClick { toast(R.string.hello) }

      }

    } padding 20.dip
  }

}
