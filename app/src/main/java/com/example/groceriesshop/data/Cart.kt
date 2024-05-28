import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.groceriesshop.presentation.home.Groceries

@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val img: Int,
    val name: String,
    val price: String,
    val quantity: String,
    val description: String,
    val count:Int=0
)



