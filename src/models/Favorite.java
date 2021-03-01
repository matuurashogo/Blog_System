package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name ="favorite")
@NamedQueries({
    @NamedQuery(
            name ="FavoriteCheck",
            query="SELECT COUNT(f) FROM Favorite AS f WHERE f.account = :user AND f.blog = :blog"
            ),
    @NamedQuery(
            name ="FavoriteGet",
            query ="SELECT f FROM Favorite AS f WHERE  f.account = :user AND f.blog = :blog"
            ),
    //Cannot create TypedQuery for query with more than one return using requested result type [models.Favorite]
    @NamedQuery(
            name ="FavoriteRanking",
            query ="SELECT f, COUNT(f.blog) FROM Favorite AS f GROUP BY f ORDER BY f.blog DESC"
            ),

})
@Entity
public class Favorite {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name ="blog_id")
    private Blog blog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

}
