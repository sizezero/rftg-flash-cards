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
  var tileButton: SImageButton = null
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
        }.wrap

        // TODO if this is last in the vertical layout then it is not visible
        this += new SLinearLayout {
          successButton = new SButton("Success")
          successButton.wrap.onClick {
            tiles = tiles match {
              case h :: t => t
              case Nil => Nil
            }
            displayTopTile()
            setTileSelected(false)
          }
          this += successButton

          failButton = new SButton("Fail")
          failButton.wrap.onClick {
            tiles = tiles match {
              case h :: t => t ++ List(h)
              case Nil => Nil
            }
            displayTopTile() 
            setTileSelected(false)
         }
          this += failButton

        }.wrap

        tileButton = new SImageButton()
        tileButton.scaleType(android.widget.ImageView.ScaleType.CENTER_INSIDE).wrap.onClick {
          setTileSelected(true)
          showDevelopment = !showDevelopment
          displayTopTile()
        }
        this += tileButton

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
    val resourceId = tiles match {
      case tile :: tail => (if (showDevelopment) R.drawable.d01 else R.drawable.w01) + tile-1
      case Nil => R.drawable.cross
    }
    tileButton.setImageResource(resourceId)

    countTextView.setText(tiles.size + "/" + NUM_TILES)
  }

  var isTileSelected: Boolean = false
  def setTileSelected(b: Boolean) {
    isTileSelected = b
    successButton.enabled(isTileSelected)
    failButton.enabled(isTileSelected)
  }
}
