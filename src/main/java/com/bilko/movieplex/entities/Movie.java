/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * Portions Copyright 2016 Dmitry Bilko
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.bilko.movieplex.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.bilko.movieplex.util.Constants.ACTORS_MAX_NUMBER;
import static com.bilko.movieplex.util.Constants.NAME_MAX_SIZE;

/**
 * JPA entity corresponding database table {@code MOVIES}.
 *
 * @see ShowTiming
 * @since 1.0
 */
@Entity
@Table(name = "MOVIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id"),
    @NamedQuery(name = "Movie.findByName", query = "SELECT m FROM Movie m WHERE m.name = :name"),
    @NamedQuery(name = "Movie.findByActors", query = "SELECT m FROM Movie m WHERE m.actors = :actors")
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private Integer id;

    @NotNull
    @Size(min = 1, max = NAME_MAX_SIZE)
    private String name;

    @NotNull
    @Size(min = 1, max = ACTORS_MAX_NUMBER)
    private String actors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private Collection<ShowTiming> showTimings;

    /**
     * Public constructor for {@code Movie} class
     */
    public Movie() { }

    /**
     * Public constructor for {@code Movie} class
     * @param id of {@code Movie}
     */
    public Movie(final Integer id) {
        this.id = id;
    }

    /**
     * Public constructor for {@code Movie} class
     * @param id of {@code Movie}
     * @param name of {@code Movie}
     * @param actors of {@code Movie}
     */
    public Movie(final Integer id, final String name, final String actors) {
        this.id = id;
        this.name = name;
        this.actors = actors;
    }

    /**
     * Returns value of {@link Movie#id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets value of {@link Movie#id}
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Returns value of {@link Movie#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets value of {@link Movie#name}
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns value of {@link Movie#actors}
     */
    public String getActors() {
        return actors;
    }

    /**
     * Sets value of {@link Movie#actors}
     */
    public void setActors(final String actors) {
        this.actors = actors;
    }

    /**
     * Returns value of {@link Movie#showTimings}
     */
    @XmlTransient
    public Collection<ShowTiming> getShowTimings() {
        return showTimings;
    }

    /**
     * Sets value of {@link Movie#showTimings}
     */
    public void setShowTimings(final Collection<ShowTiming> showTimings) {
        this.showTimings = showTimings;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (id != null) {
            hash += id.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof Movie)) {
            return false;
        }
        final Movie other = (Movie) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return name;
    }
}
