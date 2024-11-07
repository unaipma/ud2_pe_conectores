/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tresenraya;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Random;

/**
 *
 * @author cgpino
 */
public class TresEnRaya extends javax.swing.JFrame {
    
    private int jugador;
    private int modo;
    
    public String mensaje;
    public int estado;
    public int respuesta;
    public boolean valido;
    
    Random r = new Random();

    /**
     * Creates new form TresEnRaya
     */
    public TresEnRaya() {
        initComponents();
        jugador=1;
        modo=1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_casilla1 = new javax.swing.JButton();
        btn_casilla2 = new javax.swing.JButton();
        btn_casilla3 = new javax.swing.JButton();
        btn_casilla4 = new javax.swing.JButton();
        btn_casilla5 = new javax.swing.JButton();
        btn_casilla6 = new javax.swing.JButton();
        btn_casilla7 = new javax.swing.JButton();
        btn_casilla8 = new javax.swing.JButton();
        btn_casilla9 = new javax.swing.JButton();
        separadorSuperior = new javax.swing.JSeparator();
        lbl_titulo = new javax.swing.JLabel();
        lbl_turno = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        menu_partida = new javax.swing.JMenu();
        btn_1jugador = new javax.swing.JRadioButtonMenuItem();
        btn_2jugadores = new javax.swing.JRadioButtonMenuItem();
        menu_opciones = new javax.swing.JMenu();
        btn_aCercaDe = new javax.swing.JMenuItem();
        btn_reiniciar = new javax.swing.JMenuItem();
        btn_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_casilla1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla1ActionPerformed(evt);
            }
        });

        btn_casilla2.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla2ActionPerformed(evt);
            }
        });

        btn_casilla3.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla3ActionPerformed(evt);
            }
        });

        btn_casilla4.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla4ActionPerformed(evt);
            }
        });

        btn_casilla5.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla5ActionPerformed(evt);
            }
        });

        btn_casilla6.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla6ActionPerformed(evt);
            }
        });

        btn_casilla7.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla7ActionPerformed(evt);
            }
        });

        btn_casilla8.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla8ActionPerformed(evt);
            }
        });

        btn_casilla9.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btn_casilla9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_casilla9ActionPerformed(evt);
            }
        });

        lbl_titulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lbl_titulo.setText("Tres En Raya");

        lbl_turno.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbl_turno.setText("Turno de X");

        menu_partida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Component.png"))); // NOI18N
        menu_partida.setText("Partida");

        btn_1jugador.setSelected(true);
        btn_1jugador.setText("Un jugador");
        btn_1jugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Male.png"))); // NOI18N
        btn_1jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1jugadorActionPerformed(evt);
            }
        });
        menu_partida.add(btn_1jugador);

        btn_2jugadores.setText("Dos jugadores");
        btn_2jugadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/People.png"))); // NOI18N
        btn_2jugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2jugadoresActionPerformed(evt);
            }
        });
        menu_partida.add(btn_2jugadores);

        menu.add(menu_partida);

        menu_opciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Application.png"))); // NOI18N
        menu_opciones.setText("Opciones");

        btn_aCercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/About.png"))); // NOI18N
        btn_aCercaDe.setText("A cerca de");
        btn_aCercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aCercaDeActionPerformed(evt);
            }
        });
        menu_opciones.add(btn_aCercaDe);

        btn_reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Refresh.png"))); // NOI18N
        btn_reiniciar.setText("Reiniciar");
        btn_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reiniciarActionPerformed(evt);
            }
        });
        menu_opciones.add(btn_reiniciar);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Exit.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        menu_opciones.add(btn_salir);

        menu.add(menu_opciones);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separadorSuperior)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_casilla7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_casilla8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_casilla9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_casilla4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_casilla5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_casilla6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_casilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_casilla2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_casilla3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(lbl_titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(lbl_turno)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_turno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_casilla1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_casilla2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_casilla3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_casilla4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_casilla5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_casilla6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_casilla7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_casilla8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_casilla9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_casilla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla1ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla1.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla1.setForeground(Color.blue);
                btn_casilla1.setText("X");
                
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla1.setForeground(Color.red);
                btn_casilla1.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla1ActionPerformed

    private void btn_casilla2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla2ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla2.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla2.setForeground(Color.blue);
                btn_casilla2.setText("X");
                
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla2.setForeground(Color.red);
                btn_casilla2.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla2ActionPerformed

    private void btn_casilla3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla3ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla3.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla3.setForeground(Color.blue);
                btn_casilla3.setText("X");
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla3.setForeground(Color.red);
                btn_casilla3.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla3ActionPerformed

    private void btn_casilla4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla4ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla4.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla4.setForeground(Color.blue);
                btn_casilla4.setText("X");
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla4.setForeground(Color.red);
                btn_casilla4.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla4ActionPerformed

    private void btn_casilla5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla5ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla5.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla5.setForeground(Color.blue);
                btn_casilla5.setText("X");
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla5.setForeground(Color.red);
                btn_casilla5.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla5ActionPerformed

    private void btn_casilla6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla6ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla6.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla6.setForeground(Color.blue);
                btn_casilla6.setText("X");
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla6.setForeground(Color.red);
                btn_casilla6.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla6ActionPerformed

    private void btn_casilla7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla7ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla7.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla7.setForeground(Color.blue);
                btn_casilla7.setText("X");
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla7.setForeground(Color.red);
                btn_casilla7.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla7ActionPerformed

    private void btn_casilla8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla8ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla8.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla8.setForeground(Color.blue);
                btn_casilla8.setText("X");
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla8.setForeground(Color.red);
                btn_casilla8.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla8ActionPerformed

    private void btn_casilla9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_casilla9ActionPerformed
        // TODO add your handling code here:
        if (btn_casilla9.getText().isEmpty()) {
            if (getJugador()==1) {
                btn_casilla9.setForeground(Color.blue);
                btn_casilla9.setText("X");
                if (getModo()==2) {
                    setJugador(2);
                    lbl_turno.setText("Turno de O");
                }
            }else{
                btn_casilla9.setForeground(Color.red);
                btn_casilla9.setText("O");
                setJugador(1);
                lbl_turno.setText("Turno de X");
            }
            
            estado=comprobarEstado();
            
            if (getModo()==1 && estado==0) {
                jugadaOrdenador();
                estado=comprobarEstado();
            }
            
            if (estado != 0)
                finalizarPartida();
            
        }else
            JOptionPane.showMessageDialog(null,"Ya hay una ficha en esta casilla.","Jugada no válida",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_btn_casilla9ActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_aCercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aCercaDeActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Aplicación creada con Java Swing\npor Carlos Gómez Pino.","A cerca de",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btn_aCercaDeActionPerformed

    private void btn_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reiniciarActionPerformed
        // TODO add your handling code here:
        reiniciar();
    }//GEN-LAST:event_btn_reiniciarActionPerformed

    private void btn_1jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1jugadorActionPerformed
        // TODO add your handling code here:
        
        if(btn_1jugador.isSelected()) {
            setModo(1);
            reiniciar();
        }
        btn_1jugador.setSelected(true);
        btn_2jugadores.setSelected(false);
    }//GEN-LAST:event_btn_1jugadorActionPerformed

    private void btn_2jugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2jugadoresActionPerformed
        // TODO add your handling code here:
        
        if(btn_2jugadores.isSelected()) {
            setModo(2);
            reiniciar();
        }
        btn_1jugador.setSelected(false);
        btn_2jugadores.setSelected(true);
    }//GEN-LAST:event_btn_2jugadoresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TresEnRaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TresEnRaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TresEnRaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TresEnRaya.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                TresEnRaya t = new TresEnRaya();
                t.setTitle("Tres En Raya");
                t.setVisible(true);
                t.setLocationRelativeTo(null);
                t.setResizable(false);
                
                ImageIcon icono= new ImageIcon("/iconos/Application.png");
                t.setIconImage(icono.getImage());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem btn_1jugador;
    private javax.swing.JRadioButtonMenuItem btn_2jugadores;
    private javax.swing.JMenuItem btn_aCercaDe;
    private javax.swing.JButton btn_casilla1;
    private javax.swing.JButton btn_casilla2;
    private javax.swing.JButton btn_casilla3;
    private javax.swing.JButton btn_casilla4;
    private javax.swing.JButton btn_casilla5;
    private javax.swing.JButton btn_casilla6;
    private javax.swing.JButton btn_casilla7;
    private javax.swing.JButton btn_casilla8;
    private javax.swing.JButton btn_casilla9;
    private javax.swing.JMenuItem btn_reiniciar;
    private javax.swing.JMenuItem btn_salir;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_turno;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menu_opciones;
    private javax.swing.JMenu menu_partida;
    private javax.swing.JSeparator separadorSuperior;
    // End of variables declaration//GEN-END:variables
 
    public int comprobarEstado() {
        
        if(btn_casilla1.getText().equals("X") && btn_casilla2.getText().equals("X") && btn_casilla3.getText().equals("X") ||
           btn_casilla4.getText().equals("X") && btn_casilla5.getText().equals("X") && btn_casilla6.getText().equals("X") ||
           btn_casilla7.getText().equals("X") && btn_casilla8.getText().equals("X") && btn_casilla9.getText().equals("X") ||
           btn_casilla1.getText().equals("X") && btn_casilla4.getText().equals("X") && btn_casilla7.getText().equals("X") ||
           btn_casilla2.getText().equals("X") && btn_casilla5.getText().equals("X") && btn_casilla8.getText().equals("X") ||     
           btn_casilla3.getText().equals("X") && btn_casilla6.getText().equals("X") && btn_casilla9.getText().equals("X") ||
           btn_casilla1.getText().equals("X") && btn_casilla5.getText().equals("X") && btn_casilla9.getText().equals("X") ||     
           btn_casilla3.getText().equals("X") && btn_casilla5.getText().equals("X") && btn_casilla7.getText().equals("X"))
            return 1;
        
        else if(btn_casilla1.getText().equals("O") && btn_casilla2.getText().equals("O") && btn_casilla3.getText().equals("O") ||
                btn_casilla4.getText().equals("O") && btn_casilla5.getText().equals("O") && btn_casilla6.getText().equals("O") ||
                btn_casilla7.getText().equals("O") && btn_casilla8.getText().equals("O") && btn_casilla9.getText().equals("O") ||
                btn_casilla1.getText().equals("O") && btn_casilla4.getText().equals("O") && btn_casilla7.getText().equals("O") ||
                btn_casilla2.getText().equals("O") && btn_casilla5.getText().equals("O") && btn_casilla8.getText().equals("O") ||     
                btn_casilla3.getText().equals("O") && btn_casilla6.getText().equals("O") && btn_casilla9.getText().equals("O") ||
                btn_casilla1.getText().equals("O") && btn_casilla5.getText().equals("O") && btn_casilla9.getText().equals("O") ||     
                btn_casilla3.getText().equals("O") && btn_casilla5.getText().equals("O") && btn_casilla7.getText().equals("O"))
            return 2;
        
        if (btn_casilla1.getText().isEmpty() || btn_casilla2.getText().isEmpty() || btn_casilla3.getText().isEmpty() ||
            btn_casilla4.getText().isEmpty() || btn_casilla5.getText().isEmpty() || btn_casilla6.getText().isEmpty() ||
            btn_casilla7.getText().isEmpty() || btn_casilla8.getText().isEmpty() || btn_casilla9.getText().isEmpty())
            return 0;
        
        return 3;
    }
    
    public void reiniciar() {
        
        btn_casilla1.setText("");
        btn_casilla2.setText("");
        btn_casilla3.setText("");
        btn_casilla4.setText("");
        btn_casilla5.setText("");
        btn_casilla6.setText("");
        btn_casilla7.setText("");
        btn_casilla8.setText("");
        btn_casilla9.setText("");
        setJugador(1);
        lbl_turno.setText("Turno de X");
    }
    
    public void jugadaOrdenador() {
        
        valido=false;
        
        do {
            switch(r.nextInt(9)+1) {
                case 1:
                    if (btn_casilla1.getText().isEmpty()) {
                        btn_casilla1.setForeground(Color.red);
                        btn_casilla1.setText("O");
                        valido=true;
                    }
                    break;
                case 2:
                    if (btn_casilla2.getText().isEmpty()) {
                        btn_casilla2.setForeground(Color.red);
                        btn_casilla2.setText("O");
                        valido=true;
                    }
                    break;
                case 3:
                    if (btn_casilla3.getText().isEmpty()) {
                        btn_casilla3.setForeground(Color.red);
                        btn_casilla3.setText("O");
                        valido=true;
                    }
                    break;
                case 4:
                    if (btn_casilla4.getText().isEmpty()) {
                        btn_casilla4.setForeground(Color.red);
                        btn_casilla4.setText("O");
                        valido=true;
                    }
                    break;
                case 5:
                    if (btn_casilla5.getText().isEmpty()) {
                        btn_casilla5.setForeground(Color.red);
                        btn_casilla5.setText("O");
                        valido=true;
                    }
                    break;
                case 6:
                    if (btn_casilla6.getText().isEmpty()) {
                        btn_casilla6.setForeground(Color.red);
                        btn_casilla6.setText("O");
                        valido=true;
                    }
                    break;
                case 7:
                    if (btn_casilla7.getText().isEmpty()) {
                        btn_casilla7.setForeground(Color.red);
                        btn_casilla7.setText("O");
                        valido=true;
                    }
                    break;
                case 8:
                    if (btn_casilla8.getText().isEmpty()) {
                        btn_casilla8.setForeground(Color.red);
                        btn_casilla8.setText("O");
                        valido=true;
                    }
                    break;
                case 9:
                    if (btn_casilla9.getText().isEmpty()) {
                        btn_casilla9.setForeground(Color.red);
                        btn_casilla9.setText("O");
                        valido=true;
                    }
                    break;
            }
        }while(!valido);
    }
    
    public void finalizarPartida() {
        switch(estado) {
            case 1:
                mensaje="¡El jugador X gana la partida!\n¿Quieres volver a jugar?";
                break;
            case 2:
                mensaje="¡El jugador O gana la partida!\n¿Quieres volver a jugar?";
                break;
            case 3:
                mensaje="¡Partida en tablas!\n¿Quieres volver a jugar?";
                break;
        }
                
        respuesta=JOptionPane.showConfirmDialog(null,mensaje,"Fin de partida",JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_NO_OPTION)
            reiniciar();
        else
            System.exit(0);
    }

    /**
     * @return the jugador
     */
    public int getJugador() {
        return jugador;
    }

    /**
     * @param jugador the jugador to set
     */
    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    /**
     * @return the modo
     */
    public int getModo() {
        return modo;
    }

    /**
     * @param modo the modo to set
     */
    public void setModo(int modo) {
        this.modo = modo;
    }
}
