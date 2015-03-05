package org.kleemann.rftg_flash_cards

import org.scaloid.common._

// for 'sp' and other postfix ops
import scala.language.postfixOps

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

  var countTextView: STextView = null
  var tileButton: SButton = null
  var successButton: SButton = null
  var failButton: SButton = null

  val NUM_TILES = 55

  // tiles starts as a simple list of integers
  var tiles: List[Int] = null

  onCreate {
    contentView = new SFrameLayout {

      this += new SVerticalLayout {

        this += new SLinearLayout {
          countTextView = new STextView("")
          countTextView.wrap
          this += countTextView
          SButton("Restart").wrap.onClick {
            resetTiles()
          }
        }

        tileButton = new SButton("Tile")
        tileButton.textSize(24.5 sp).onClick {
          setTileSelected(true)
          showDevelopment = !showDevelopment
          displayTopTile()
        }
        this += tileButton

        this += new SLinearLayout {
          successButton = new SButton("Success")
          successButton.wrap.onClick {
            tiles = tiles match {
              case h :: t => t
              case Nil => Nil
            }
            displayTopTile()
          }
          this += successButton

          failButton = new SButton("Fail")
          failButton.wrap.onClick {
            tiles = tiles match {
              case h :: t => t ++ List(h)
              case Nil => Nil
            }
            displayTopTile()
          }
          this += failButton

        }
      }

    } padding 20.dip

    resetTiles()
  }

  var showDevelopment: Boolean = true

  def resetTiles() {
     tiles = (1 to NUM_TILES).toList
     showDevelopment = true
     displayTopTile()
     setTileSelected(false)
  }

  // must be called after the top tile has changed
  def displayTopTile() {
    val text = tiles match {
      case tile :: tail => (if (showDevelopment) "d" else "w") + f"$tile%02d"
      case Nil => "Nil"
    }
    tileButton.setText(text)

    countTextView.setText(tiles.size + "/" + NUM_TILES)
  }

  var isTileSelected: Boolean = false
  def setTileSelected(b: Boolean) {
    isTileSelected = b
    successButton.enabled(isTileSelected)
    failButton.enabled(isTileSelected)
  }
}
