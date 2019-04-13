/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.automata;

/**
 *
 * @author admin
 */
public enum FinalStates implements State{
    
    q0(0),
    q1(1),
    q2(2),
    q3(3),
    q4(4),
    q5(5),
    q6(6),
    q7(7),
    q8(8),
    q9(9),
    q10(10),
    q11(11),
    q12(12),
    q13(13),
    q14(14),
    q15(15),
    q16(16),
    q17(17),
    q18(18),
    q19(19),
    q20(20),
    q21(21),
    q22(22),
    q23(23),
    q24(24),
    q25(25),
    q26(26),
    q27(27),
    q28(28),
    q29(29),
    q30(30),
    q31(31),
    q32(32),
    q33(33),
    q34(34),
    q35(35),
    q36(36),
    q37(37),
    q38(38),
    q39(39),
    q40(40),
    q41(41),
    q42(42),
    q43(43),
    q44(44),
    q45(45),
    q46(46),
    q47(47),
    q48(48),
    q49(49),
    q50(50),
    q51(51),
    q52(52),
    q53(53),
    q54(54),
    q55(55),
    q56(56),
    q57(57),
    q58(58),
    q59(59),
    q60(60),
    q61(61),
    q62(62),
    q63(63),
    q64(64),
    q65(65),
    q66(66),
    q67(67),
    q68(68),
    q69(69),
    q70(70),
    q71(71),
    q72(72),
    q73(73),
    q74(74),
    q75(75),
    q76(76),
    q77(77),
    q78(78),
    q79(79),
    q80(80),
    q81(81),
    q82(82),
    q83(83),
    q84(84),
    q85(85),
    q86(86),
    q87(87),
    q88(88),
    q89(89),
    q90(90),
    q91(91),
    q92(92),
    q93(93),
    q94(94),
    q95(95),
    q96(96),
    q97(97),
    q98(98),
    q99(99),
    q100(100),
    q101(101),
    q102(102),
    q103(103),
    q104(104),
    q105(105),
    q106(106),
    q107(107),
    q108(108),
    q109(109),
    q110(110),
    q111(111),
    q112(112),
    q113(113),
    q114(114),
    q115(115),
    q116(116),
    q117(117),
    q118(118),
    q119(119),
    q120(120),
    q121(121),
    q122(122),
    q123(123),
    q124(124),
    q125(125),
    q126(126),
    q127(127),
    q128(128),
    q129(129),
    q130(130),
    q131(131),
    q132(132),
    q133(133),
    q134(134),
    q135(135),
    q136(136),
    q137(137),
    q138(138),
    q139(139),
    q140(140),
    q141(141),
    q142(142),
    q143(143);
    
    public int code;
    public boolean lookAhead;
    
    
    FinalStates(int code){
        this.code=code;
        this.lookAhead=false;
    }

    @Override
    public int getCode() {
        return code;
    }
    
    @Override
    public boolean hasLookedAhead(){
        return Info.lookAhead;
    }
    
    @Override
    public State next(String input){return this;};
    
    @Override
    public boolean isFinal(){return true;}
}
