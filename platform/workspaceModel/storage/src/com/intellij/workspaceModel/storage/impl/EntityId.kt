// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.workspaceModel.storage.impl

import com.intellij.workspaceModel.storage.WorkspaceEntity

internal fun createEntityId(arrayId: Int, clazz: Int): EntityId {
  return EntityId(arrayId, clazz)
}

internal data class EntityId(val arrayId: Int, val clazz: Int) {
  init {
    if (arrayId < 0) error("ArrayId cannot be negative: $arrayId")
  }

  override fun toString(): String = clazz.findEntityClass<WorkspaceEntity>().simpleName + "-:-" + arrayId.toString()
}

// Implementation of EntityId that is packed to a single long
/*

private fun createPackedEntityId(arrayId: Int, clazz: Int) = arrayId.toLong() shl 32 or (clazz.toLong() and 0xffffffffL)

typealias EntityId = Long

val EntityId.arrayId: Int
  get() = (this shr 32).toInt()

val EntityId.clazz: Int
  get() = this.toInt()

fun EntityId.asString() = clazz.findEntityClass<WorkspaceEntity>().simpleName + "-:-" + arrayId.toString()

fun EntityId.copy(arrayId: Int = this.arrayId, clazz: Int = this.clazz): EntityId {
  return createEntityId(arrayId, clazz)
}

*/
