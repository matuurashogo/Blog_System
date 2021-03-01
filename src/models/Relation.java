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

@Table(name ="relation")
@NamedQueries({
    @NamedQuery(
            name = "checkfollowCount",
            query = "SELECT COUNT(r) FROM Relation AS r WHERE r.following = :following AND r.followers = :followers"
            ),
    @NamedQuery(
            name = "getfollow",
            query = "SELECT r FROM Relation AS r WHERE r.following = :following AND r.followers = :followers"
            ),
    @NamedQuery(
            name = "getFollowList",
          query ="SELECT r FROM Relation AS r WHERE r.following = :following"
            ),

})

@Entity
public class Relation {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private Account following;

    @ManyToOne
    @JoinColumn(name ="followers_id")
    private Account followers;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getFollowing() {
        return following;
    }

    public void setFollowing(Account following) {
        this.following = following;
    }

    public Account getFollowers() {
        return followers;
    }

    public void setFollowers(Account followers) {
        this.followers = followers;
    }



}
