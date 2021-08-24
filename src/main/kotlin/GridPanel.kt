import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel


/**
 * Grid Panel at Frame.
 */
object GridPanel : JPanel() {

    private lateinit var g2D: Graphics2D
    const val GRID_SIDE = 801
    private const val GRID_GAP = 1
    private const val FONT_SIZE = 24
    private const val NODE_SIZE = GRID_SIDE / Grid.ROWS



    override fun paintComponent(g: Graphics) {
        g2D = g as Graphics2D

        g2D.color = Color.GRAY
        g2D.fillRect(0, 0, GRID_SIDE, GRID_SIDE)


        for (i in 0 until Grid.ROWS) {
            for (j in 0 until Grid.ROWS) {
                g2D.drawRect(i * NODE_SIZE, j * NODE_SIZE, NODE_SIZE, NODE_SIZE)
            }
        }

        paintGrid()
    }


    /**
     * Paints the grid.
     */
    private fun paintGrid() {
        font = Font("ARIAL", Font.BOLD, FONT_SIZE)
        g2D.font = font

        for (i in 0 until Grid.size) {
            for (j in 0 until Grid.size) {
                val node = Grid.getNodeAt(Position(j, i))!!
                g2D.color = node.getColor()
                g2D.fillRect(
                    i * NODE_SIZE + GRID_GAP,
                    j * NODE_SIZE + GRID_GAP,
                    NODE_SIZE - GRID_GAP,
                    NODE_SIZE - GRID_GAP
                )

                if (node.type == Type.END || node.type == Type.START) {
                    val str = if (node.type == Type.START) "S" else "E"
                    g2D.color = Color.BLACK
                    g2D.drawString(
                        str,
                        i * NODE_SIZE + (NODE_SIZE - g2D.fontMetrics.stringWidth(str)) / 2,
                        j * NODE_SIZE + 1 + g2D.fontMetrics.ascent
                                + (NODE_SIZE - g2D.fontMetrics.height) / 2
                    )
                }
            }
        }
    }

}
