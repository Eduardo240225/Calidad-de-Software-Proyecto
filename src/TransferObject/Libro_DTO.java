package TransferObject;

/**
 * Clase DTO (Data Transfer Object) que representa un libro en el sistema.
 * Contiene los atributos relacionados con la identificación, categoría,
 * editorial, título, año de publicación y stock del libro.
 */
public class Libro_DTO {

    /**
     * Identificador único del libro.
     */
    private int id;

    /**
     * Identificador de la categoría a la que pertenece el libro.
     */
    private int id_categoria;

    /**
     * Identificador de la editorial del libro.
     */
    private int id_editorial;

    /**
     * Título del libro.
     */
    private String titulo;

    /**
     * Año de publicación del libro.
     */
    private int año_publicacion;

    /**
     * Cantidad de ejemplares disponibles en stock.
     */
    private int stock;

    /**
     * Obtiene el ID del libro.
     *
     * @return ID del libro.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del libro.
     *
     * @param id Nuevo ID del libro.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID de la categoría del libro.
     *
     * @return ID de la categoría.
     */
    public int getId_categoria() {
        return id_categoria;
    }

    /**
     * Establece el ID de la categoría del libro.
     *
     * @param id_categoria Nuevo ID de la categoría.
     */
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    /**
     * Obtiene el ID de la editorial del libro.
     *
     * @return ID de la editorial.
     */
    public int getId_editorial() {
        return id_editorial;
    }

    /**
     * Establece el ID de la editorial del libro.
     *
     * @param id_editorial Nuevo ID de la editorial.
     */
    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return Título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo Nuevo título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el año de publicación del libro.
     *
     * @return Año de publicación.
     */
    public int getAño_publicacion() {
        return año_publicacion;
    }

    /**
     * Establece el año de publicación del libro.
     *
     * @param año_publicacion Nuevo año de publicación.
     */
    public void setAño_publicacion(int año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    /**
     * Obtiene el stock disponible del libro.
     *
     * @return Cantidad de ejemplares en stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad de stock del libro.
     *
     * @param stock Nueva cantidad de stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
