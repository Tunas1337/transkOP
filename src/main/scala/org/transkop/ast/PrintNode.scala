package org.transkop.ast

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes._
import org.transkop.SymbolTable


case class PrintNode(operand: AstNode) extends StatementNode {
  def generate(mv: MethodVisitor, symbolTable: SymbolTable) {
    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    operand.generate(mv, symbolTable)
    if (operand.isInstanceOf[StringNode]) {
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V")
    }
    else {
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V")
    }
  }
}

