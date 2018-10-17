package com.rwds.erecruitment.user.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-z. ]*", message = "Nama Depan Harus Huruf")
    private String namadepan;

    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Za-z, ]*", message = "Nama Belakang Harus Huruf")
    private String namabelakang;

    @NotNull(message = "Email Harus Diisi")
    @NotEmpty(message = "Email Tidak Boleh Kosong")
    @Email(message = "Email Harus Menggunakan Format Email")
    private String surel;

    @Size(min = 8, max = 50)
    @Pattern(regexp = "[A-Za-z.0-9]*", message = "Katasandi Harus Terdiri Dari Huruf Dan Angka")
    private String katasandi;

    @Size(min = 6, max = 20)
    @Pattern(regexp = "[A-Za-z.0-9 ]*", message = "Nomor Telepon Hanya Bisa Terdiri Dari Alphanumeric")
    private String telepon;

    @Pattern(regexp = "[0-9]*", message = "Umur Hanya Terdiri Dari Angka")
    private int usia;

    public String getSurel() {
        return surel;
    }

    public Integer getId() {
        return id;
    }

}