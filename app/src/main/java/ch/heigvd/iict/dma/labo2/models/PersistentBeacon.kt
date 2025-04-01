package ch.heigvd.iict.dma.labo2.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import org.altbeacon.beacon.Beacon
import java.time.Instant
import java.util.*

/*
 *  N'hésitez pas à ajouter des attributs ou des méthodes à cette classe
 */
data class PersistentBeacon(
    var id : Long = nextId++,
    var major: Int,
    var minor: Int,
    var uuid: UUID,
    var rssi : Int,
    var txPower : Int,
    var distance : Double,
    var lastSeenTime : Long) {

    companion object {
        private var nextId = 0L

        fun fromBeacon(beacon: Beacon) : PersistentBeacon{
            return PersistentBeacon(
                major = beacon.id2.toInt(),
                minor = beacon.id3.toInt(),
                uuid = beacon.id1.toUuid(),
                rssi = beacon.rssi,
                txPower = beacon.txPower,
                distance = beacon.distance,
                lastSeenTime = System.currentTimeMillis()
            )
        }
    }

    
}

class PersistentBeaconDiffCallback(private val oldList : List<PersistentBeacon>,
                                   private val newList : List<PersistentBeacon> ) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return  old.major == new.major &&
                old.minor == new.minor &&
                old.uuid  == new.uuid &&
                old.rssi  == new.rssi &&
                old.txPower  == new.txPower &&
                old.distance == new.distance
    }

}


